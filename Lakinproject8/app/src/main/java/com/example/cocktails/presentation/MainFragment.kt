package com.example.cocktails.presentation

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_SEARCH
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cocktails.R
import com.example.cocktails.data.CocktailsAPI
import com.example.cocktails.data.models.CocktailsRemote
import com.example.cocktails.databinding.MainFragmentBinding
import com.example.cocktails.domain.Cocktail
import com.example.cocktails.presentation.details.DetailsView
import com.example.cocktails.presentation.main.MainView
import com.example.cocktails.presentation.main.MainViewModel
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainFragment: Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MainView(viewModel, ::openDetailsFragment)
            }
        }
    }

    private fun openDetailsFragment(cocktail: Cocktail) {

        val fragment = DetailFragment()
        fragment.arguments = bundleOf(
            DetailFragment.COCKTAIL_KEY to cocktail.id
        )

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect {
                    it.error?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}