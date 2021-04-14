package com.carlostorres.virtusdigital.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.virtusdigital.R
import com.carlostorres.virtusdigital.data.local.entity.Items
import com.carlostorres.virtusdigital.model.interfacs.HomeListener

class ItemsAdapter(
    var itemsList: List<Items>,
    private var clickItem: HomeListener):
    RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    class ItemsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.tvItemTitle)
        var tvAuthorAndCreateAt: TextView = view.findViewById(R.id.tvItemAuthorCreatedAt)

        @SuppressLint("SetTextI18n")
        fun initialize(items: Items, action: HomeListener ) {
            tvTitle.text = items.title
            tvAuthorAndCreateAt.text = "${items.author} - ${items.created_at}"

            itemView.setOnClickListener {
                action.itemClicked(items, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemsViewHolder(inflater.inflate(R.layout.recyclerview_items, parent, false))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.initialize(itemsList[position], clickItem)
    }

    override fun getItemCount() = itemsList.size

}