package com.pro.film_viewer.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.film_viewer.adapters.TopFilmsAdapter
import com.pro.film_viewer.databinding.FragmentHomeBinding
import com.pro.film_viewer.retrofit.ConnectionLiveData
import com.pro.film_viewer.viewModel.HomeFilmsViewModel
import com.pro.film_viewer.viewModel.HomeFilmsViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var topFilmsAdapter: TopFilmsAdapter
    private lateinit var topFilmsViewModel: HomeFilmsViewModel
    private lateinit var connectionLiveData: ConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = HomeFilmsViewModelFactory()
        topFilmsViewModel = ViewModelProvider(this, viewModelFactory)[HomeFilmsViewModel::class.java]
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
        observeNetwork()

        prepareRecyclerAdapter()
        observeTopFilms()
        topFilmsViewModel.getTopFilms()
        onFilmClickListener()

        onSearchIconClickListener()
    }

    private fun observeNetwork() {
        connectionLiveData = ConnectionLiveData(requireActivity().application)
        connectionLiveData.observe(viewLifecycleOwner){

        }

    }


    private fun onSearchIconClickListener() {
        binding.ivSearchIcon.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeMenuItemToSearchFragment()
            findNavController().navigate(action)
        }
    }

    private fun onFilmClickListener() {
        topFilmsAdapter.onClick = {
            film ->
            val action = HomeFragmentDirections.actionHomeMenuItemToDetailFilmFragment(film.kinopoiskId.toString())
            findNavController().navigate(action)
        }
    }

    private fun prepareRecyclerAdapter() {
        topFilmsAdapter = TopFilmsAdapter()
        val dividerItemDecoration = DividerItemDecoration(binding.rvFilms.context, LinearLayoutManager.VERTICAL)
        binding.rvFilms.addItemDecoration(dividerItemDecoration)
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