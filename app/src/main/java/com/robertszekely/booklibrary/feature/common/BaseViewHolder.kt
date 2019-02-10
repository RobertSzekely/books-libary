package com.robertszekely.booklibrary.feature.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class BaseViewHolder<out VB : ViewDataBinding> constructor(val binding: VB) : RecyclerView.ViewHolder(binding.root)