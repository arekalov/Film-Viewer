package com.pro.film_viewer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pro.film_viewer.R
import com.pro.film_viewer.databinding.FragmentDetailFilmBinding
import com.pro.film_viewer.viewModel.DetailFilmViewModel

class DetailFilmFragment : Fragment() {
    companion object {
        const val FILM_ID = "filmId"
    }
    lateinit var binding: FragmentDetailFilmBinding
    lateinit var detailFilmViewModel: DetailFilmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailFilmBinding.inflate(inflater)
        detailFilmViewModel = DetailFilmViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(FILM_ID)?.let { detailFilmViewModel.getDetailFilm(it) }
        observeDetailFilmViewModel()
    }

    private fun observeDetailFilmViewModel() {
        detailFilmViewModel.observeDetailFilmLiveData().observe(viewLifecycleOwner, Observer {
            film ->
            binding.tvTitle.text = film.nameRu
            binding.tvDescription.text = film.description
            binding.tvGenres.text = getString(R.string.testGenre) + " " +film.getStringGenres()
            binding.tvCountries.text = getString(R.string.countries) + " " +film.getStringCountries()
            Glide.with(this).load(film.posterUrl).into(binding.ivPoster)
        })
    }

}