package com.example.trp.data.mappers.tasks

import com.google.gson.annotations.SerializedName

data class StudentRegistration(
    @SerializedName("username") val username: String? = null,
    @SerializedName("fullName") val fullName: String? = null,
    @SerializedName("password") val password: String? = null
)