package com.robertszekely.booklibrary.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robertszekely.booklibrary.DetailFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    private val viewModel by viewModel<DetailViewModel> { parametersOf(arguments?.argBookId) }
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        private const val ARG_BOOK_ID = "arg.BOOK_ID"

        fun newInstance(bookId: String) = DetailFragment().apply {
            arguments?.putString(ARG_BOOK_ID, bookId)
        }

        private val Bundle.argBookId
            get() = getString(ARG_BOOK_ID)
    }

}
