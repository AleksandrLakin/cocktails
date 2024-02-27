package com.example.mymessage.domain

import com.google.gson.annotations.SerializedName

data class RegisterData(
    val username: String,
    val password: String,
    val lastName: String = "",
    val firstName: String = ""
)