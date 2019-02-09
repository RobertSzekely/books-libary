package com.example.booklibrary.feature.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.booklibrary.data.models.Book
import com.example.booklibrary.databinding.ItemBookBinding
import com.example.booklibrary.feature.common.BaseListAdapter

class BookAdapter : BaseListAdapter<Book, ItemBookBinding>(BOOK_DIFF_UTIL) {

    override fun createBinding(parent: ViewGroup): ItemBookBinding {
        return ItemBookBinding.inflate(LayoutInflater.from(parent.context))
    }

    override fun bind(binding: ItemBookBinding, item: Book) {
        binding.book = item
    }

    companion object {
        val BOOK_DIFF_UTIL = object : DiffUtil.ItemCallback<Book>() {

            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}