package com.pro.film_viewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pro.data.models.FilmOfTop
import com.pro.film_viewer.databinding.FilmCardBinding

class TopFilmsAdapter : RecyclerView.Adapter<TopFilmsAdapter.TopFilmsViewHolder>() {
    inner class TopFilmsViewHolder(val binding: FilmCardBinding) : RecyclerView.ViewHolder(binding.root)
    private var topFilmList = ArrayList<FilmOfTop>()
    var onClick: ((FilmOfTop) -> Unit)? = null

    // TODO: add difutil
    fun setTopFilmsList(list: List<FilmOfTop>) {
        topFilmList = list as ArrayList<FilmOfTop>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopFilmsViewHolder {
        return TopFilmsViewHolder(
            FilmCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return topFilmList.size
    }
    override fun onBindViewHolder(holder: TopFilmsViewHolder, position: Int) {
        holder.binding.tvFilmTitle.text = topFilmList[position].nameRu
        holder.binding.tvGenre.text = topFilmList[position].getStringGenres()
        holder.binding.tvYear.text = topFilmList[position].year.toString()
        Glide.with(holder.itemView)
            .load(topFilmList[position].posterUrlPreview)
            .into(holder.binding.ivFilmBanner)
        holder.itemView.setOnClickListener{
            onClick!!.invoke(topFilmList[position])
        }
    }
}