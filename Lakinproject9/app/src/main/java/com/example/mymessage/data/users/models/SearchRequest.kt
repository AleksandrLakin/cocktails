package com.example.mymessage.data.users.models

import com.google.gson.annotations.SerializedName

data class SearchRequest (
    @SerializedName("search_string") val query: String
)