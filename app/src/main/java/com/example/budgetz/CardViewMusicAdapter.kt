package com.example.budgetz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewMusicAdapter(private val listMusic: ArrayList<Music>) : RecyclerView.Adapter<CardViewMusicAdapter.CardViewViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return CardViewViewHolder(view)

    }


    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val (name, from, photo) = listMusic[position]

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMusic[holder.adapterPosition]) }

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)


        holder.tvName.text = name
        holder.tvFrom.text = from


        holder.btnFavorite.setOnClickListener { Toast.makeText(holder.itemView.context, "Favorite " + listMusic[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }


        holder.btnShare.setOnClickListener { Toast.makeText(holder.itemView.context, "Share " + listMusic[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }


        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Kamu memilih " + listMusic[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }

    }

    override fun getItemCount(): Int {
        return listMusic.size
    }


    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)


    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Music)
    }


}