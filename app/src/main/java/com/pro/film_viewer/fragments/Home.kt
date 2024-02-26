package com.pro.film_viewer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.film_viewer.R
import com.pro.film_viewer.adapters.TopFilmsAdapter
import com.pro.film_viewer.databinding.FragmentHomeBinding
import com.pro.film_viewer.viewModel.TopFilmsViewModel
import com.pro.film_viewer.viewModel.TopFilmsViewModelFactory


class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var topFilmsAdapter: TopFilmsAdapter
    private lateinit var topFilmsViewModel: TopFilmsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = TopFilmsViewModelFactory()
        topFilmsViewModel = ViewModelProvider(this, viewModelFactory)[TopFilmsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerAdapter()
        observeTopFilms()
        topFilmsViewModel.getTopFilms()
    }

    private fun prepareRecyclerAdapter() {
        topFilmsAdapter = TopFilmsAdapter()
        binding.rvFilms.apply {
            adapter = topFilmsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeTopFilms() {
        topFilmsViewModel.observeTopFilmsLiveData().observe(viewLifecycleOwner, Observer {
            films ->
            topFilmsAdapter.setTopFilmsList(films)
        })
    }
}