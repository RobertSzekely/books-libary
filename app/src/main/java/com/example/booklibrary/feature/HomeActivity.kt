package com.example.booklibrary.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.booklibrary.R
import com.example.booklibrary.databinding.HomeActivityBinding
import com.example.booklibrary.feature.feed.BookFeedFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.activity_home)

        if (savedInstanceState == null) {
            navigateToFragment(BookFeedFragment.newInstance())
        }

        setSupportActionBar(binding.toolbar)
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit()
    }

}
