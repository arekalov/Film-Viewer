package com.pro.film_viewer.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.film_viewer.adapters.SearchedFilmsAdapter
import com.pro.film_viewer.adapters.TopFilmsAdapter
import com.pro.film_viewer.databinding.FragmentSearchBinding
import com.pro.film_viewer.viewModel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var filmsAdapter: SearchedFilmsAdapter
    private lateinit var searchedFilmsViewModel: SearchViewModel

    companion object {
        fun newInstance() = SearchFragment()
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
        searchedFilmsViewModel = SearchViewModel()
        onClickSearhIcon()
        observeSearchedFilmsLiveData()
        prepareAdapter()
        onFilmCardClickListener()
        observeEditSearchLine()
    }

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
//            Log.e("id", film.kinopoiskId.toString())
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
                filmsAdapter.setTopFilmsList(films.films)
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