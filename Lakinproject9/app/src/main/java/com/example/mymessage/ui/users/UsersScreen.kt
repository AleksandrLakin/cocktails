package com.example.mymessage.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun UsersScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: UsersViewModel
) {
    val state = viewModel.observeUi().collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            items = state.value.users,
            key = {it.id},
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(text = it.username)
                    Text(text = it.firstName)
                    Text(text = it.lastName)
                }
            }
        }
    }


}