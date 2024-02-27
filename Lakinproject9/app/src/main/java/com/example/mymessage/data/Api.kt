package com.example.mymessage.data

import com.example.mymessage.data.auth.models.AuthRemote
import com.example.mymessage.data.auth.models.AuthResponse
import com.example.mymessage.data.auth.models.RegisterRemote
import com.example.mymessage.data.users.models.SearchRequest
import com.example.mymessage.data.users.models.SearchResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/login")
    suspend fun logIn(@Body auth: AuthRemote): AuthResponse

    @POST("/register")
    suspend fun register(@Body register: RegisterRemote): AuthResponse

    @POST("/search")
    suspend fun search(@Body request: SearchRequest): SearchResponse

}