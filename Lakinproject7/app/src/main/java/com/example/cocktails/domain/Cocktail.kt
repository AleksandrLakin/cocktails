package com.example.cocktails.domain

data class Cocktail (
    val id: String,
    val name: String,
    val src: String?,
    val ingredient1: String? = null,
    val ingredient2: String? = null,
    val ingredient3: String? = null,
    val ingredient4: String? = null,
    val ingredient5: String? = null,
    val measure1: String? = null,
    val measure2: String? = null,
    val measure3: String? = null,
    val measure4: String? = null,
    val measure5: String? = null
)