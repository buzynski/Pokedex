package com.buzynski.pokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzynski.pokedex.base.BaseFragment
import com.buzynski.pokedex.base.BaseViewModel
import com.buzynski.pokedex.databinding.ViewMainBinding
import com.buzynski.pokedex.rv.adapter.MainViewAdapter
import com.buzynski.pokedex.viewmodel.MainViewViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment() {

    private lateinit var binding: ViewMainBinding
    private val viewModel: MainViewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewMainBinding.inflate(inflater, container, false)
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
        val adapter = MainViewAdapter()
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.recyclerView.adapter = adapter
    }
}