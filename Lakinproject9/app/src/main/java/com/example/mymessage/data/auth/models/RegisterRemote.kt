package com.example.mymessage.data.auth.models

import com.google.gson.annotations.SerializedName

data class RegisterRemote(
    val username: String,
    val password: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String
)