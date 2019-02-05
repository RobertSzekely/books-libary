package com.example.booklibrary.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import com.example.booklibrary.R
import com.example.booklibrary.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.activity_home)
    }

}
