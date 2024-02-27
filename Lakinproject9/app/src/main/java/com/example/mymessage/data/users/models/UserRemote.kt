package com.example.mymessage.data.users.models

import com.google.gson.annotations.SerializedName

data class UserRemote (
    val id: Int,
    val username: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String,
)