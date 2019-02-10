package com.example.booklibrary.feature.feed.addbook

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibrary.R

class AddBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, AddBookActivity::class.java)
    }
}
