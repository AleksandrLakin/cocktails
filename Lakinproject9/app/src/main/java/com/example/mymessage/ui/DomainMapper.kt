package com.example.cocktails.presentation

import android.util.Log
import com.example.cocktails.data.models.Drink
import com.example.cocktails.domain.Ingredient

fun Drink.toItems(): List<Ingredient> {

    val items = mutableListOf<Ingredient>()
    Log.d("TAG", "$this")
    items += Ingredient(0, this.strIngredient1.orEmpty(), this.strMeasure1.orEmpty())
    items += Ingredient(1, this.strIngredient2.orEmpty(), this.strMeasure2.orEmpty())
    items += Ingredient(2, this.strIngredient3.orEmpty(), this.strMeasure3.orEmpty())
    items += Ingredient(3, this.strIngredient4.orEmpty(), this.strMeasure4.orEmpty())
    items += Ingredient(5, this.strIngredient5.orEmpty(), this.strMeasure5.orEmpty())
    items += Ingredient(6, this.strIngredient6.orEmpty(), this.strMeasure6.orEmpty())
    items += Ingredient(7, this.strIngredient7.orEmpty(), this.strMeasure7.orEmpty())
    items += Ingredient(8, this.strIngredient8.orEmpty(), this.strMeasure8.orEmpty())
    items += Ingredient(9, this.strIngredient9.orEmpty(), this.strMeasure9.orEmpty())
    items += Ingredient(10, this.strIngredient10.orEmpty(), this.strMeasure10.orEmpty())
    items += Ingredient(11, this.strIngredient11.orEmpty(), this.strMeasure11.orEmpty())
    items += Ingredient(12, this.strIngredient12.orEmpty(), this.strMeasure12.orEmpty())
    items += Ingredient(13, this.strIngredient13.orEmpty(), this.strMeasure13.orEmpty())
    items += Ingredient(14, this.strIngredient14.orEmpty(), this.strMeasure14.orEmpty())
    items += Ingredient(15, this.strIngredient15.orEmpty(), this.strMeasure15.orEmpty())

    return items
}