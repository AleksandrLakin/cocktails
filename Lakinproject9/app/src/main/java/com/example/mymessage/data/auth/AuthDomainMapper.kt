package com.example.mymessage.data.auth

import com.example.mymessage.data.auth.models.RegisterRemote
import com.example.mymessage.domain.RegisterData

fun RegisterData.asRemote() = RegisterRemote(
    username = username,
    password = password,
    lastName = lastName,
    firstName = firstName
)