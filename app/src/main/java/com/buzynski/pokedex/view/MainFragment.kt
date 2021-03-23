package com.buzynski.pokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzynski.pokedex.base.BaseFragment
import com.buzynski.pokedex.base.BaseViewModel
import com.buzynski.pokedex.databinding.FragmentMainViewBinding
import com.buzynski.pokedex.rv.adapter.MainViewAdapter
import com.buzynski.pokedex.viewmodel.MainViewViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment(), UserAction {

    // GOT MEMORY LEAK DUE TO LATEINIT PROPERTY WHICH AFTER DESTROY DOESN'T CLEARED, REPLACED THAT
    // https://developer.android.com/topic/libraries/view-binding#fragments
    private var _binding: FragmentMainViewBinding? = null
    private val viewModel: MainViewViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = getViewModel() as MainViewViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.fetchData()
        configureRecyclerView()
    }

    // ---

    override fun getViewModel(): BaseViewModel = viewModel

    // --- RECYCLERVIEW

    private fun configureRecyclerView() {
        val adapter = MainViewAdapter(this)
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.recyclerView.adapter = adapter
    }

    // ---

    override fun onItemCellClicked(pokemonName: String, pokemonId: Int) {
        viewModel.userPokemonClicked(pokemonName, pokemonId)
    }

    // ---

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}