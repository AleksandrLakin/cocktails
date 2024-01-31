package com.example.cocktails.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cocktails.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsView(model: DetailsViewModel) {
    val state = model.observeUi().collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = state.value.title
            )

            GlideImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(top = 8.dp),
                model = state.value.src,
                contentDescription = null,
                failure = placeholder(R.drawable.error),
                loading = placeholder(R.drawable.error)
            )

            LazyColumn(modifier = Modifier
                .padding(top = 8.dp)
            ) {
                items(
                    items = state.value.items,
                    key = { it.id }
                ) {
                    IngredientView(it)
                }
            }
        }
    }
}