package com.wanted.wanted_news.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wanted.wanted_news.R
import com.wanted.wanted_news.base.BaseActivity
import com.wanted.wanted_news.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        with(binding.bottomNavMain) {
            setupWithNavController(navController)
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.topNewsFragment -> {
                        navController.navigate(R.id.topNewsFragment)
                    }
                    R.id.categoryFragment -> {
                        navController.navigate(R.id.categoryFragment)
                    }
                    R.id.savedFragment -> {
                        navController.navigate(R.id.savedFragment)
                    }
                }
                true
            }
        }
    }
}