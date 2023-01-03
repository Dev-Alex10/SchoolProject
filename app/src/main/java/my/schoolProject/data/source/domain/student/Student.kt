package my.schoolProject.data.source.domain.student

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val name: String,
    val password: String,
    val email: String,
    val internalId: Long
) : Parcelable
