package com.robertszekely.booklibrary.feature.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.robertszekely.booklibrary.R
import com.robertszekely.booklibrary.databinding.HomeActivityBinding
import com.robertszekely.booklibrary.feature.favorites.FavoritesFragment
import com.robertszekely.booklibrary.feature.feed.addbook.AddBookActivity
import com.robertszekely.booklibrary.feature.wishlist.WishlistFragment
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
            fab.setOnClickListener {
                startActivity(AddBookActivity.getStartIntent(this@HomeActivity))
            }
            navigationView.setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> {
                        fab.show()
                        navigateToFragment(HomeFragment.newInstance())
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
            navigateToFragment(HomeFragment.newInstance())
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
