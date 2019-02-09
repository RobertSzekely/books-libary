package com.example.booklibrary.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.booklibrary.R
import com.example.booklibrary.databinding.HomeActivityBinding
import com.example.booklibrary.feature.favorites.FavoritesFragment
import com.example.booklibrary.feature.feed.BookFeedFragment
import com.example.booklibrary.feature.wishlist.WishlistFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.activity_home).apply {
            setSupportActionBar(this.toolbar)
            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> {
                        fab.show()
                        navigateToFragment(BookFeedFragment.newInstance())
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.favorites -> {
                        fab.hide()
                        navigateToFragment(FavoritesFragment.newInstance())
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.wishlist -> {
                        fab.hide()
                        navigateToFragment(WishlistFragment.newInstance())
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
        }

        if (savedInstanceState == null) {
            navigateToFragment(BookFeedFragment.newInstance())
        }

    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit()
    }

}
