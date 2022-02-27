package developer.unam.practicocoppel.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import developer.unam.practicocoppel.databinding.AdapterMovieBinding
import developer.unam.practicocoppel.retrofit.character.Characters
import developer.unam.practicocoppel.retrofit.character.Result
import java.lang.Exception

class AdapterCharacterMain(private val context: onClickAdapterCharacter?) :
    RecyclerView.Adapter<AdapterCharacterMain.MainViewHolder>() {
    var characters = mutableListOf<Result>()

    interface onClickAdapterCharacter {
        fun clickCharacterAdapter(character: Result)
    }


    fun insertItems(cartModels: MutableList<Result>) {
        val sizePrimer = characters.size
        characters.addAll(cartModels)
        notifyItemRangeInserted(sizePrimer, characters.size)

    }

    inner class MainViewHolder(private val bindingAdapter: AdapterMovieBinding) :
        RecyclerView.ViewHolder(bindingAdapter.root) {
        val imageHero = bindingAdapter.ivAdapterHeroes
        val tvHero = bindingAdapter.tvAdapterName
        val progress = bindingAdapter.pbAdapterMovie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            AdapterMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val character = characters[position]
        holder.tvHero.text = character.name
        val imagePath = character.thumbnail?.path
        val extension = character.thumbnail?.extension
        val imageFinal = "$imagePath.$extension"

        holder.itemView.setOnClickListener {
            if(context is onClickAdapterCharacter)
                context.clickCharacterAdapter(character)
            else
                Log.e("adapterC","adapterClick")
        }
        Picasso.Builder(holder.itemView.context).build().load(imageFinal).into(holder.imageHero, object : Callback {
            override fun onSuccess() {
                holder.progress.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                holder.progress.visibility = View.GONE
            }
        })
    }

    override fun getItemCount(): Int = characters.size
}