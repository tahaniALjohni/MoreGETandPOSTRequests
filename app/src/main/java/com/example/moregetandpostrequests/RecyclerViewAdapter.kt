package com.example.moregetandpostrequests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moregetandpostrequests.databinding.ItemRowBinding

class RecyclerViewAdapter(private val entries: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entry = entries[position]

        holder.binding.apply {
            tvItem.text = entry
        }
    }

    override fun getItemCount() = entries.size

    fun update(){
        notifyDataSetChanged()
    }
}