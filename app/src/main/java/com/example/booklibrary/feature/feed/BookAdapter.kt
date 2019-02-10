package com.example.booklibrary.feature.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.booklibrary.R
import com.example.booklibrary.data.models.Book
import com.example.booklibrary.databinding.ItemBookBinding
import com.example.booklibrary.feature.common.BaseListAdapter

class BookAdapter : BaseListAdapter<Book, ItemBookBinding>(BookDiff) {

    override fun createBinding(parent: ViewGroup): ItemBookBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_book, parent, false)
    }

    override fun bind(binding: ItemBookBinding, item: Book) {
        binding.book = item
    }
}

object BookDiff : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}