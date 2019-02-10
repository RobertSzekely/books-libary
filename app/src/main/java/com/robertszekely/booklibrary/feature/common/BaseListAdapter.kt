package com.robertszekely.booklibrary.feature.common

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


abstract class BaseListAdapter<T, V : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, BaseViewHolder<V>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        return BaseViewHolder(createBinding(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    protected abstract fun bind(binding: V, item: T)
}
