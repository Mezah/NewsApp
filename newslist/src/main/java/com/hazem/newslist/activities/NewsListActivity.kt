package com.hazem.newslist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hazem.newslist.R
import com.hazem.newslist.di.NewsListModule
import com.hazem.newslist.viewmodels.NewsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.StringQualifier

class NewsListActivity : AppCompatActivity() {
    private lateinit var navHosFragment: NavHostFragment
    private lateinit var navController: NavController
    private val newsListViewModel: NewsListViewModel by viewModel<NewsListViewModel>(StringQualifier(NewsListModule.NEWS_LIST_VM))
    
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        navHosFragment = supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        navController = navHosFragment.navController
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController)
    }

}
