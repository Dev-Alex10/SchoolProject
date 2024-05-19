package com.example.login.domain.student

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    val name: String,
    val password: String,
    val email: String,
    val internalId: Long
) : Parcelable
