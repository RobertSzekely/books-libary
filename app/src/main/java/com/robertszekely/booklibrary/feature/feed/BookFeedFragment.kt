package com.robertszekely.booklibrary.feature.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.robertszekely.booklibrary.BookFeedFragmentBinding
import com.robertszekely.booklibrary.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookFeedFragment : Fragment() {

    private val viewModel: BookFeedViewModel by viewModel()
    private lateinit var binding: BookFeedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_feed, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = BookAdapter()
        with(binding) {
            viewModel = this@BookFeedFragment.viewModel
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(context)
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
