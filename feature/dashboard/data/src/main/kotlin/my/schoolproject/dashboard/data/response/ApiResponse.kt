import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val valuesList: List<T> = emptyList(),
    val lastUpdated: Long? = null,
)
