package com.example.cocktails.presentation.details

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.cocktails.data.CocktailsAPI
import com.example.cocktails.data.models.CocktailsRemote
import com.example.cocktails.domain.Ingredient
import com.example.cocktails.presentation.toItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsViewModel: ViewModel() {

    private val state = MutableStateFlow(ViewState())
    fun observeUi() = state.asStateFlow()

    val logger = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BASIC
    }
    var client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()


    val api = Retrofit
        .Builder()
        .baseUrl("https://www.thecocktaildb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(CocktailsAPI::class.java)

    fun getData(cocktailId: String) {
        val response = api.getCocktailById(cocktailId)
        response.enqueue(object : Callback<CocktailsRemote> {
            override fun onResponse(call: Call<CocktailsRemote>, response: Response<CocktailsRemote>) {
                val body = response.body()
                body?.let {response ->
                    val item = response.drinks?.first() ?: return
                    state.update { it.copy(
                        title = item.strDrink,
                        src = item.strDrinkThumb,
                        items = item.toItems()
                    ) }
                }

            }

            override fun onFailure(call: Call<CocktailsRemote>, t: Throwable) {
                Log.d("TAG", "$t")
            }

        })
    }

}

data class ViewState(
    val title: String = "",
    val src: String? = null,
    val items: List<Ingredient> = emptyList()
)
