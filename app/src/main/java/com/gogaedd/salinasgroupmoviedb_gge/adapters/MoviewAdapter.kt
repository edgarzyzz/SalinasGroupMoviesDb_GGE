package com.gogaedd.salinasgroupmoviedb_gge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gogaedd.salinasgroupmoviedb_gge.databinding.ItemMovieBinding
import com.gogaedd.salinasgroupmoviedb_gge.listeners.MovieListener
import com.gogaedd.salinasgroupmoviedb_gge.model.Movie

class MoviewAdapter(): RecyclerView.Adapter<MoviewAdapter.MoviewViewHolder>() {

    val mlistMovies = mutableListOf<Movie>()
    var mListener : MovieListener? = null


    fun setListener(listener: MovieListener){
        mListener = listener

    }


    fun updateElements(moviesUpdated: MutableList<Movie>){
        mlistMovies.clear()
        mlistMovies.addAll(moviesUpdated)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val binding =  ItemMovieBinding.inflate(inflater,parent, false)
        return MoviewViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) = holder.bind(mlistMovies[position])

    override fun getItemCount(): Int = mlistMovies.size
    class MoviewViewHolder(val binding: ItemMovieBinding, val mListener: MovieListener?): RecyclerView.ViewHolder(binding.root){
        fun bind(moview: Movie){
            binding.movie =  moview
            binding.root.setOnClickListener {
                mListener?.onClickMovie(moview)
            }



        }

    }
}