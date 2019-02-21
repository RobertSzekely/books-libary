package com.robertszekely.booklibrary.feature.feed.addbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.robertszekely.booklibrary.AddBookBinding
import com.robertszekely.booklibrary.R
import com.robertszekely.booklibrary.data.models.Book
import com.robertszekely.booklibrary.util.EventObserver
import com.robertszekely.booklibrary.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_add_book.authorInput
import kotlinx.android.synthetic.main.activity_add_book.coverInput
import kotlinx.android.synthetic.main.activity_add_book.titleInput
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddBookActivity : AppCompatActivity() {

    private val viewModel: AddBookViewModel by viewModel()
    lateinit var binding: AddBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<AddBookBinding>(this, R.layout.activity_add_book).apply {
            saveButton.setOnClickListener {
                currentFocus?.hideKeyboard()
                validateAndAddBook()
            }
        }

        viewModel.snackBarEvent.observe(this, EventObserver { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun validateAndAddBook() {
        if (!binding.titleInput.text.isNullOrEmpty()) {
            if (!binding.authorInput.text.isNullOrEmpty()) {
                if (!binding.coverInput.text.isNullOrEmpty()) {
                    viewModel.addBook(Book(null, titleInput.text.toString(), authorInput.text.toString(), coverInput.text.toString()))
                } else {
                    binding.textInputCover.error = resources.getString(R.string.add_book_error_cover)
                }
            } else {
                binding.textInputAuthor.error = resources.getString(R.string.add_book_error_author)
            }
        } else {
            binding.textInputTitle.error = resources.getString(R.string.add_book_error_title)
        }
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, AddBookActivity::class.java)
    }
}
