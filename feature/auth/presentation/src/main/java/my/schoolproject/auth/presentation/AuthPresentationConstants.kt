package my.schoolproject.auth.presentation

object AuthPresentationConstants {
    const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z0-9.-]+\$"

    /**
     * - `^`                        - Start of values anchor.
     * - `(?=.*[a-z])`              - Ensure at least one lowercase letter.
     * - `(?=.*[A-Z])`              - Ensure at least one uppercase letter.
     * - `(?=.*\\d)`                - Ensure at least one digit.
     * - `(?=.*[@$!%*?&])`          - Ensure at least one special character from the specified set.
     * - `[A-Za-z\\d@$!%*?&]{8,}`   - The password must contain 8 or more characters from the allowed set.
     * - `$                         - End of values anchor.
     **/
    const val PASSWORD_REGEX =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"
}