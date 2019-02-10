package com.example.booklibrary.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.booklibrary.HomeFragmentBinding
import com.example.booklibrary.R
import com.example.booklibrary.feature.feed.BookFeedFragment


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentManager?.beginTransaction()?.replace(R.id.content, BookFeedFragment.newInstance())?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

    }
}
