package com.hazem.newslist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hazem.newslist.R

class NewsListActivity : AppCompatActivity() {
    private lateinit var navHosFragment: NavHostFragment
    private lateinit var navController: NavController

    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        navHosFragment = supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        navController = navHosFragment.navController
        toolbar = findViewById(R.id.main_toolbar)
        toolbar.setupWithNavController(navController)
    }

}
