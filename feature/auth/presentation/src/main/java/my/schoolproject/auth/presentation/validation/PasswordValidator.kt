package my.schoolproject.auth.presentation.validation

object PasswordValidator {

    /**
     * - `^`                        - Start of values anchor.
     * - `(?=.*[a-z])`              - Ensure at least one lowercase letter.
     * - `(?=.*[A-Z])`              - Ensure at least one uppercase letter.
     * - `(?=.*\\d)`                - Ensure at least one digit.
     * - `(?=.*[@$!%*?&])`          - Ensure at least one special character from the specified set.
     * - `[A-Za-z\\d@$!%*?&]{8,}`   - The password must contain 8 or more characters from the allowed set.
     * - `$                         - End of values anchor.
     **/
    const val PASSWORD_PATTERN =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"

    fun validate(password: String): Boolean {
        return PASSWORD_PATTERN.toRegex().matches(password)
    }
}