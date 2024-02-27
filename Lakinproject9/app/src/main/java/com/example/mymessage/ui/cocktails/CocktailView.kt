package com.example.cocktails.presentation.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cocktails.domain.Cocktail
import com.example.mymessage.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun CocktailView(
    item: Cocktail,
    onClick: (Cocktail) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = { onClick.invoke(item) }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.Center,
            text = item.name
        )
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .width(150.dp)
                .height(150.dp)
                .padding(top = 8.dp, bottom = 16.dp),
            alignment = Alignment.Center,
            model = item.src,
            contentDescription = null,
            failure = placeholder(R.drawable.error),
            loading = placeholder(R.drawable.error)
        )
    }
}