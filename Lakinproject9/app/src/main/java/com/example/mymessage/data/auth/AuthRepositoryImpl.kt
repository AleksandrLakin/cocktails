package com.example.mymessage.data.auth

import android.util.Log
import com.example.mymessage.data.Api
import com.example.mymessage.data.auth.models.AuthRemote
import com.example.mymessage.domain.RegisterData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: Api
) : AuthRepository {
    override suspend fun login(login: String, password: String): Boolean {
        val auth = AuthRemote(username = login, password = password)
        return withContext(Dispatchers.IO) {
            try {
                api.logIn(auth)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("TAG", e.message ?: "хуета")
                false
            }
        }
    }

    override suspend fun register(registerData: RegisterData): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                api.register(registerData.asRemote())
                true
            } catch (e: Exception) {
                Log.d("TAG", e.message ?: "хуета")
                false
            }
        }
    }
}