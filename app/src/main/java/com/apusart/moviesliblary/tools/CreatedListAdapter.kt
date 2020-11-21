package com.apusart.moviesliblary.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apusart.moviesliblary.R
import com.apusart.moviesliblary.api.CreatedList
import kotlinx.android.synthetic.main.created_list_item.view.*

class CreatedListAdapter: ListAdapter<CreatedList, CreatedListViewHolder>(diffCallback) {
    object diffCallback: DiffUtil.ItemCallback<CreatedList>() {
        override fun areItemsTheSame(oldItem: CreatedList, newItem: CreatedList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CreatedList, newItem: CreatedList): Boolean {
            return oldItem.name == newItem.name
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedListViewHolder {
        val container = LayoutInflater.from(parent.context)
            .inflate(R.layout.created_list_item, parent, false)

        return CreatedListViewHolder(container)
    }

    override fun onBindViewHolder(holder: CreatedListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CreatedListViewHolder(container: View): RecyclerView.ViewHolder(container) {

    fun bind(list: CreatedList) {
        itemView.apply {
            created_list_item_list_description.text = list.description
            created_list_item_name.text = list.name
            created_list_item_items_count.text = list.item_count.toString()
            created_list_item_list_type.text = list.list_type
        }
    }
}