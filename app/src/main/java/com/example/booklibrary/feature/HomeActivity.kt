package com.example.booklibrary.feature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.booklibrary.R
import com.example.booklibrary.databinding.HomeActivityBinding
import com.example.booklibrary.feature.favorites.FavoritesFragment
import com.example.booklibrary.feature.feed.BookFeedFragment
import com.example.booklibrary.feature.wishlist.WishlistFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomDrawerBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.activity_home).apply {
            setSupportActionBar(bottomAppBar)
            bottomDrawerBehavior = BottomSheetBehavior.from(bottomDrawer)
            bottomDrawerBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            bottomAppBar.setNavigationOnClickListener { bottomDrawerBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED }
            bottomAppBar.replaceMenu(R.menu.main_navigation)
            navigationView.setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> {
                        fab.show()
                        navigateToFragment(BookFeedFragment.newInstance())
                        return@setNavigationItemSelectedListener true
                    }
                    R.id.favorites -> {
                        fab.hide()
                        navigateToFragment(FavoritesFragment.newInstance())
                        return@setNavigationItemSelectedListener true
                    }
                    R.id.wishlist -> {
                        fab.hide()
                        navigateToFragment(WishlistFragment.newInstance())
                        return@setNavigationItemSelectedListener true
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
        bottomDrawerBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (bottomDrawerBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomDrawerBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            return
        }
        super.onBackPressed()
    }

}
