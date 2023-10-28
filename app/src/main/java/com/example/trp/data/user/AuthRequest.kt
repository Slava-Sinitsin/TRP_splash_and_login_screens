package com.example.trp.data.user

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("username") val mail: String? = null,
    @SerializedName("password") val password: String? = null
)