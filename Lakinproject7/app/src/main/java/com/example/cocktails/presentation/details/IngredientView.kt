package com.example.cocktails.presentation.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.cocktails.domain.Ingredient

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun IngredientView(model: Ingredient) {
    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = model.measure
        )
        Text(
            modifier = Modifier.weight(1f),
            text = model.name
        )
        GlideImage(
            modifier = Modifier.weight(1f),
            model ="https://www.thecocktaildb.com/images/ingredients/${model.name}-Medium.png",
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun IngredientViewPreview(){
    val model = Ingredient(1, "Ингредиент", "Измерение")
    IngredientView(model)
}

data class IngredientModel(
    val ingredient: String,
    val measure: String,
    val icon: String
)