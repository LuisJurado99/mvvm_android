package developer.unam.practicocoppel.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.unam.practicocoppel.databinding.AdapterHeroGenericBinding
import developer.unam.practicocoppel.retrofit.character.Item

class AdapterGenericDetails: RecyclerView.Adapter<AdapterGenericDetails.ViewGenericAdapter>() {
    private val result = mutableListOf<Item>()

    inner class ViewGenericAdapter(itemView: AdapterHeroGenericBinding) : RecyclerView.ViewHolder(itemView.root){
        val name = itemView.tvGenericName
    }

    fun addItems(result:MutableList<Item>){
        val sizeUlti = result.size
        this.result.addAll(result)
        Log.e("Adapter","bull $result")
        Log.e("Adapter","result ${this.result}")
        notifyItemRangeInserted(sizeUlti,result.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewGenericAdapter =
        ViewGenericAdapter(AdapterHeroGenericBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))

    override fun onBindViewHolder(holder: ViewGenericAdapter, position: Int) {
        val item = result[position]
        Log.e("Adapter","bull ${item.name}")
        holder.name.text = item.name
    }

    override fun getItemCount(): Int = result.size

}