package my.schoolProject.data.source.domain.student

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import my.schoolProject.data.source.local.student.LicenceType

@Parcelize
data class Student(
    val name: String,
    val password: String,
    val email: String,
    val internalId: Long,
    val licenseId: Long,
    val licenseType: LicenceType
) : Parcelable
