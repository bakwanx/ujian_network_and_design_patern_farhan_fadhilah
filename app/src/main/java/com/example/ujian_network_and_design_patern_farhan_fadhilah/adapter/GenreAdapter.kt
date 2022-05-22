package com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.activities.HomeActivity
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Genre
import kotlinx.coroutines.*

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    private var genres: List<Genre> = ArrayList()
    var checkedPosition = 0

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvGenre: TextView = itemView.findViewById(R.id.tv_genre)
        val cvGenre: CardView = itemView.findViewById(R.id.cv_genre)

        fun bind(genre: Genre, checkedPosition: Int) {
            tvGenre.text = genre.name
            if (checkedPosition == -1) {
                tvGenre.setTextColor(itemView.resources.getColor(R.color.black))
                cvGenre.setCardBackgroundColor(itemView.resources.getColor(R.color.white))
            } else {
                if (checkedPosition == adapterPosition) {
                    tvGenre.setTextColor(itemView.resources.getColor(R.color.white))
                    cvGenre.setCardBackgroundColor(itemView.resources.getColor(R.color.toska))
                } else {
                    tvGenre.setTextColor(itemView.resources.getColor(R.color.black))
                    cvGenre.setCardBackgroundColor(itemView.resources.getColor(R.color.white))
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) {
        holder.bind(genres.get(position), checkedPosition)
        holder.itemView.setOnClickListener {
            holder.tvGenre.setTextColor(holder.itemView.resources.getColor(R.color.white))
            holder.cvGenre.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.toska))
            if (checkedPosition != position) {
                notifyItemChanged(checkedPosition)
                checkedPosition = position
                (holder.itemView.context as HomeActivity).fetchMovie(genres.get(position).id.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    fun setItem(listGenre: List<Genre>){
        if(listGenre!=null){
            this.genres = listGenre
        }
    }

    fun getSelected(): Genre? {
        var genre: Genre? =null

        if (genres.isNotEmpty()){
            if (checkedPosition != -1){
                genre = genres.get(checkedPosition)
            }
        }
        return genre
    }

}