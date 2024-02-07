package com.example.cocktails.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktails.R
import com.example.cocktails.domain.Cocktail

@Composable
fun MainView(
    model: MainViewModel,
    onClickCocktail: (Cocktail) -> Unit
) {
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
            Row(
                verticalAlignment =  Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Поиск") }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_search_24),
                    contentDescription = "Поиск",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { model.search(text) }
                )
            }

            LazyColumn(modifier = Modifier
                .padding(top = 8.dp)
            ) {
                items(
                    items = state.value.items,
                    key = { it.id }
                ) {
                    CocktailView(it, onClickCocktail)
                }
            }


        }
    }
}