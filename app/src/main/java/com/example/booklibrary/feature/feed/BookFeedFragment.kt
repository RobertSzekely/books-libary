package com.example.booklibrary.feature.feed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.booklibrary.BookFeedFragmentBinding

import com.example.booklibrary.R

class BookFeedFragment : Fragment() {

    private lateinit var viewModel: BookFeedViewModel
    private lateinit var binding: BookFeedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_feed_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookFeedViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = BookFeedFragment()
    }

}
