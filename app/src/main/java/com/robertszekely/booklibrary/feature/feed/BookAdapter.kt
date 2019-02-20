package com.robertszekely.booklibrary.feature.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.robertszekely.booklibrary.R
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.databinding.ItemBookBinding
import com.robertszekely.booklibrary.feature.common.BaseListAdapter
import com.robertszekely.booklibrary.feature.common.BookNavigationActions

class BookAdapter(private val bookNavigationActionsListener: BookNavigationActions) : BaseListAdapter<Book, ItemBookBinding>(BookDiff) {

    override fun createBinding(parent: ViewGroup): ItemBookBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_book, parent, false)
    }

    override fun bind(binding: ItemBookBinding, item: Book) {
        binding.book = item
        binding.bookNavigationListener = bookNavigationActionsListener
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