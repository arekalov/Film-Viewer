package com.pro.film_viewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pro.film_viewer.databinding.FilmCardBinding
import com.pro.film_viewer.pojo.FilmSearched

class SearchedFilmsAdapter : RecyclerView.Adapter<SearchedFilmsAdapter.SearchedFilmViewHolder>() {
    inner class SearchedFilmViewHolder(val binding: FilmCardBinding) : RecyclerView.ViewHolder(binding.root)
    private var searchedFilmList = ArrayList<FilmSearched>()
    var onClick: ((FilmSearched) -> Unit)? = null

    // TODO: add difutil
    fun setTopFilmsList(list: List<FilmSearched>) {
        searchedFilmList = list as ArrayList<FilmSearched>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedFilmViewHolder {
        return SearchedFilmViewHolder(
            FilmCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return searchedFilmList.size
    }
    override fun onBindViewHolder(holder: SearchedFilmViewHolder, position: Int) {
        holder.binding.tvFilmTitle.text = searchedFilmList[position].nameRu
        holder.binding.tvGenre.text = searchedFilmList[position].getStringGenres()
        holder.binding.tvYear.text = searchedFilmList[position].year
        Glide.with(holder.itemView)
            .load(searchedFilmList[position].posterUrlPreview)
            .into(holder.binding.ivFilmBanner)
        holder.itemView.setOnClickListener{
            onClick!!.invoke(searchedFilmList[position])
        }
    }
}