package com.robertszekely.booklibrary.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.robertszekely.booklibrary.BookDetailFragmentBinding
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.util.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BookDetailFragment : Fragment() {

    private val viewModel by viewModel<BookDetailViewModel> { parametersOf(arguments?.argBookId) }
    private lateinit var binding: BookDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BookDetailFragmentBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        binding.updateButton.setOnClickListener {
            updateBook()
        }

        viewModel.snackBarEvent.observe(this, EventObserver { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun updateBook() {
        if (binding.bookTitle.text.toString().isNotEmpty()) {
            if (binding.bookAuthor.text.toString().isNotEmpty()) {
                viewModel.updateBook(Book(null, binding.bookTitle.text.toString(), binding.bookAuthor.text.toString(), viewModel.cover.value))
            }
        }

    }

    companion object {
        private const val ARG_BOOK_ID = "arg.BOOK_ID"

        fun newInstance(bookId: String) = BookDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_BOOK_ID, bookId)
            }
        }

        private val Bundle.argBookId
            get() = getString(ARG_BOOK_ID)
    }

}
