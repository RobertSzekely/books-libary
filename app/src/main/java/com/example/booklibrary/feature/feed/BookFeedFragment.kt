package com.example.booklibrary.feature.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.booklibrary.BookFeedFragmentBinding
import com.example.booklibrary.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookFeedFragment : Fragment() {

    private val viewModel: BookFeedViewModel by viewModel()
    private lateinit var binding: BookFeedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_feed_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = BookAdapter()
        with(binding) {
            viewModel = this@BookFeedFragment.viewModel
            recycler.adapter = adapter
            setLifecycleOwner(viewLifecycleOwner)
        }
        viewModel.booksList.observe(viewLifecycleOwner, Observer {result ->
            if (!result.isNullOrEmpty()) {
                adapter.submitList(result)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadBooks()
    }

    companion object {
        fun newInstance() = BookFeedFragment()
    }

}
