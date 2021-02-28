package com.buzynski.pokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.buzynski.pokedex.base.BaseFragment
import com.buzynski.pokedex.base.BaseViewModel
import com.buzynski.pokedex.databinding.FragmentPokemonPreviewBinding
import com.buzynski.pokedex.extensions.getFrontImageUrlFromId
import com.buzynski.pokedex.navigation.MainViewArgs
import com.buzynski.pokedex.viewmodel.PokemonPreviewViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonPreviewFragment: BaseFragment() {

    private val args: MainViewArgs by navArgs()

    private lateinit var binding: FragmentPokemonPreviewBinding
    private val viewModel: PokemonPreviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonPreviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.imgUrl = args.pokemonId.getFrontImageUrlFromId()
        binding.pokemonName = args.pokemonName
        binding.viewModel = getViewModel() as PokemonPreviewViewModel

        viewModel.fetchData(args.pokemonId)

        return binding.root
    }

    // ---

    override fun getViewModel(): BaseViewModel = viewModel
}