package com.example.cocktails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cocktails.R
import com.example.cocktails.presentation.details.DetailsView
import com.example.cocktails.presentation.details.DetailsViewModel


class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val cocktailId by lazy { requireArguments().getString(COCKTAIL_KEY, "") }
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                DetailsView(viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData(cocktailId)
    }


    companion object {
        const val COCKTAIL_KEY = "cocktailId"
    }

}