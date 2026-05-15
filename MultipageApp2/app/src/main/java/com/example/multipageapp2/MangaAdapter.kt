package com.example.multipageapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MangaAdapter(
    private val mangaList: List<Manga>,
    private val onClick: (Manga) -> Unit
) : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    inner class MangaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCover: ImageView = view.findViewById(R.id.imgCover)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtGenre: TextView = view.findViewById(R.id.txtGenre)
        val txtSummary: TextView = view.findViewById(R.id.txtSummary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_manga, parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = mangaList[position]
        holder.txtTitle.text = manga.title
        holder.txtGenre.text = manga.genre
        holder.txtSummary.text = manga.summary
        Glide.with(holder.itemView.context)
            .load(manga.imageUrl)
            .placeholder(android.R.color.darker_gray)
            .into(holder.imgCover)
        holder.itemView.setOnClickListener { onClick(manga) }
    }

    override fun getItemCount() = mangaList.size
}