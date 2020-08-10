package com.kenruizinoue.retrofittemplate3

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.movie_photo)
    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val overview: TextView = itemView.findViewById(R.id.movie_overview)
    private val rating: TextView = itemView.findViewById(R.id.movie_rating)

    fun bind(movie: Result) {
        Glide.with(itemView.context)
            .load("http://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(photo)
        title.text = "Title: ${movie.title}"
        overview.text = movie.overview
        rating.text = "Rating : ${ movie.vote_average}"
    }
}