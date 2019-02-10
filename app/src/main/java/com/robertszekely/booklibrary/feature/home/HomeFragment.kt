package com.robertszekely.booklibrary.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robertszekely.booklibrary.HomeFragmentBinding
import com.robertszekely.booklibrary.R
import com.robertszekely.booklibrary.feature.feed.BookFeedFragment


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
