package my.schoolproject.auth.presentation.validation

object EmailValidator {
    const val EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z0-9.-]+\$"

    fun validate(email: String): Boolean {
        return EMAIL_PATTERN.toRegex().matches(email)
    }
}