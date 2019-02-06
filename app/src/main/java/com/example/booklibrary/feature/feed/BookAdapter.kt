package com.example.booklibrary.feature.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booklibrary.data.models.Book
import com.example.booklibrary.databinding.ItemBookBinding

class BookAdapter : ListAdapter<Book, BookAdapter.ViewHolder>(BookDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        holder.apply {
            bind(View.OnClickListener {  }, book)
            itemView.tag = book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class ViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Book) {
            binding.apply {
                clickListener = listener
                book = item
                executePendingBindings()
            }
        }
    }
}

private class BookDiffCallback : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}