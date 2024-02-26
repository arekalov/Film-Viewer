package com.pro.film_viewer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.film_viewer.databinding.FilmCardBinding
import com.pro.film_viewer.pojo.Item

class TopFilmsAdapter : RecyclerView.Adapter<TopFilmsAdapter.TopFilmsViewHolder>() {
    inner class TopFilmsViewHolder(val binding: FilmCardBinding) : RecyclerView.ViewHolder(binding.root)
    private var topFilmList = ArrayList<Item>()

    // TODO: add difutil
    fun setTopFilmsList(list: List<Item>) {
        topFilmList = list as ArrayList<Item>
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

    // TODO: add all fields
    override fun onBindViewHolder(holder: TopFilmsViewHolder, position: Int) {
        holder.binding.ivFilmTitle.text = topFilmList[position].nameRu
    }
}