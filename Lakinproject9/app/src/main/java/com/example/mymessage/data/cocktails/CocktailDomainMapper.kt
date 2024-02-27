package com.example.mymessage.data.cocktails

import com.example.cocktails.data.models.Drink
import com.example.cocktails.domain.Cocktail

fun Drink.asDomain() = Cocktail(
    id = this.idDrink,
    name = this.strDrink,
    src = this.strDrinkThumb,
)

fun List<Drink>.asDomain() = this.map { it.asDomain() }