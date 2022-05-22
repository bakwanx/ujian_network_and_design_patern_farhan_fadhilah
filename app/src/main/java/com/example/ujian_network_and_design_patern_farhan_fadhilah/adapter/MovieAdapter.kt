package com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.activities.MovieDetailActivity
import com.example.ujian_network_and_design_patern_farhan_fadhilah.api.GenreResponse
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Genre
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Movie

class MovieAdapter(val context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movies: List<Movie> = ArrayList()
    private var idGenre: String = String()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(movie: Movie, context: Context, idGenre: String){
            tvTitle.setText(movie.getTitle())
            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w342${movie.getPosterPath()}")
                .centerCrop()
                .placeholder(R.drawable.ic_no_img)
                .into(ivMovie);
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra(KEY_MOVIE, movie)
                intent.putExtra(KEY_ID_GENRE, idGenre)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movie = movies.get(position), context = context, idGenre)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setIdGenre(idGenre: String){
        this.idGenre = idGenre
    }

    fun setItem(listMovie: List<Movie>){
        if (listMovie != null){
            this.movies = listMovie
        }
    }

    companion object{
        val KEY_MOVIE = "key_movie"
        val KEY_ID_GENRE = "key_id_genre"
    }

}