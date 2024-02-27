package com.example.mymessage.data.users

import com.example.mymessage.domain.User

interface UsersRepository {

    suspend fun getUsers(): List<User>

}