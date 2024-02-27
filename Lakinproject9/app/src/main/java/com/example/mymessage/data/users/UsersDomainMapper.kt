package com.example.mymessage.data.users

import com.example.mymessage.data.users.models.UserRemote
import com.example.mymessage.domain.User

fun UserRemote.asDomain() = User(
    id = id,
    username = username,
    lastName = lastName,
    firstName = firstName
)

fun List<UserRemote>.asDomain() = this.map { it.asDomain() }