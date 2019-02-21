package com.robertszekely.booklibrary.feature.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.robertszekely.booklibrary.BookFeedFragmentBinding
import com.robertszekely.booklibrary.R
import com.robertszekely.booklibrary.feature.detail.BookDetailFragment
import com.robertszekely.booklibrary.util.EventObserver
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
        val adapter = BookAdapter(viewModel)
        with(binding) {
            viewModel = this@BookFeedFragment.viewModel
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(context)
            setLifecycleOwner(viewLifecycleOwner)
            swipeRefreshLayout.setOnRefreshListener {
                this@BookFeedFragment.viewModel.loadBooks()
            }
        }
        viewModel.booksList.observe(viewLifecycleOwner, Observer {result ->
            if (!result.isNullOrEmpty()) {
                adapter.submitList(result)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressCircular.show()
            } else {
                binding.progressCircular.hide()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        viewModel.navigateToBookAction.observe(viewLifecycleOwner, EventObserver {
            fragmentManager?.transaction {
                replace(R.id.main_content, BookDetailFragment.newInstance(it))
                addToBackStack(null)
            }
        })
    }

    companion object {
        fun newInstance() = BookFeedFragment()
    }

}
