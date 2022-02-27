package developer.unam.practicocoppel.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import developer.unam.practicocoppel.databinding.AdapterMovieBinding
import developer.unam.practicocoppel.retrofit.character.Characters
import developer.unam.practicocoppel.retrofit.character.Result
import java.lang.Exception

class AdapterCharacterMain(private val context: Context) :
    RecyclerView.Adapter<AdapterCharacterMain.MainViewHolder>() {
    var characters = mutableListOf<Result>()
    fun insertItems(cartModels: MutableList<Result>) {
        val sizePrimer = characters.size
        characters.addAll(cartModels)
        notifyItemRangeInserted(sizePrimer, characters.size)

    }

    inner class MainViewHolder(private val bindingAdapter: AdapterMovieBinding) :
        RecyclerView.ViewHolder(bindingAdapter.root) {
        val imageHero = bindingAdapter.ivAdapterHeroes
        val tvHero = bindingAdapter.tvAdapterName
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
        val imagePath = character.thumbnail.path
        val extension = character.thumbnail.extension
        val imageFinal = "$imagePath.$extension"
        Picasso.Builder(context).build().load(imageFinal).into(holder.imageHero)
    }

    override fun getItemCount(): Int = characters.size
}