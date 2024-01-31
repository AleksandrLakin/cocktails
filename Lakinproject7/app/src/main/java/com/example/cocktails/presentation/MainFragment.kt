package com.example.cocktails.presentation

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_SEARCH
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cocktails.R
import com.example.cocktails.data.CocktailsAPI
import com.example.cocktails.data.models.CocktailsRemote
import com.example.cocktails.databinding.MainFragmentBinding
import com.example.cocktails.domain.Cocktail
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainFragment: Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    private lateinit var adapter: CocktailAdapter

    val api = Retrofit
        .Builder()
        .baseUrl("https://www.thecocktaildb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CocktailsAPI::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CocktailAdapter() {
            openDetailsFragment(it.id)
        }
        binding.content.adapter = adapter
        binding.content.layoutManager = FlexboxLayoutManager(requireContext())
        binding.search.setOnClickListener {
            search()
        }
        binding.editText.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            true
        }
    }

    private fun search() {
        val text = binding.editText.text.toString()

        val response = if (text.length == 1) {
            try {
                api.searchCocktails(text)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                return
            }
        } else {
            try {
                api.searchCocktailsByName(text)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                return
            }

        }

        response.enqueue(object : Callback<CocktailsRemote> {
            override fun onResponse(call: Call<CocktailsRemote>, response: Response<CocktailsRemote>) {
                val body = response.body()
                body?.let {
                    val result = it.drinks?.map {
                        Cocktail(
                            id = it.idDrink,
                            name = it.strDrink,
                            src = it.strDrinkThumb,
                        )
                    } ?: emptyList()
                    adapter.submitList(result)
                }

            }

            override fun onFailure(call: Call<CocktailsRemote>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun openDetailsFragment(cocktailId: String) {

        val fragment = DetailFragment()
        fragment.arguments = bundleOf(
            DetailFragment.COCKTAIL_KEY to cocktailId
        )

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()

    }

}