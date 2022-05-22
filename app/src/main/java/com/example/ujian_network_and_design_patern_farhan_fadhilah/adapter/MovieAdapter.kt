package com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.api.GenreResponse
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Genre
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Movie

class MovieAdapter(val context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movies: List<Movie> = ArrayList()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(movie: Movie, context: Context){
            tvTitle.setText(movie.title)
            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .centerCrop()
                .placeholder(R.drawable.ic_no_img)
                .into(ivMovie);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movie = movies.get(position), context = context)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setItem(listMovie: List<Movie>){
        if (listMovie != null){
            this.movies = listMovie
        }
    }

}