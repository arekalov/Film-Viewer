package com.pro.film_viewer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.film_viewer.adapters.SearchedFilmsAdapter
//import com.pro.film_viewer.retrofit.ConnectionLiveData
import com.pro.film_viewer.viewModel.SearchViewModel
import com.pro.presentation.R
import com.pro.presentation.databinding.FragmentSearchBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var filmsAdapter: SearchedFilmsAdapter
    private lateinit var searchedFilmsViewModel: SearchViewModel
//    private lateinit var connectionLiveData: ConnectionLiveData

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.home_menu_item)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        connectionLiveData = ConnectionLiveData(requireActivity().application)
//        observeConnection()

        searchedFilmsViewModel = SearchViewModel()
        onClickSearhIcon()
        observeSearchedFilmsLiveData()
        prepareAdapter()
        onFilmCardClickListener()
        observeEditSearchLine()
    }

//    private fun observeConnection() {
//        connectionLiveData.observe(viewLifecycleOwner){isConnected->
//            if (!isConnected) {
//                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToNoInternetFragment())
//            }
//        }
//    }

    private fun observeEditSearchLine() {
        var searchJob: Job? = null
        binding.etSearchLine.addTextChangedListener {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                searchedFilmsViewModel.searchFilms(it.toString())
                delay(500)
            }
        }
    }

    private fun onFilmCardClickListener() {
        filmsAdapter.onClick = {
            film ->
            Log.e("fsdafdsaf", film.toString())
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFilmFragment(film.filmId.toString())
                findNavController().navigate(action)
        }
    }

    private fun prepareAdapter() {
        filmsAdapter = SearchedFilmsAdapter()
        binding.rvFilms.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeSearchedFilmsLiveData() {
        searchedFilmsViewModel.observeSearchedFilmsLiveData().observe(viewLifecycleOwner, Observer {
            films->
            if (films.searchFilmsCountResult != 0) {
                binding.rvFilms.visibility = View.VISIBLE
                binding.ivNotFound.visibility = View.INVISIBLE
                filmsAdapter.setTopFilmsList(films.films)
            }
            else {
                binding.rvFilms.visibility = View.INVISIBLE
                binding.ivNotFound.visibility = View.VISIBLE
            }
        })
    }

    private fun onClickSearhIcon() {
        binding.ivSearchIcon.setOnClickListener {
            searchFilms()
        }
    }

    private fun searchFilms() {
        searchedFilmsViewModel.searchFilms(binding.etSearchLine.text.toString())
    }


}