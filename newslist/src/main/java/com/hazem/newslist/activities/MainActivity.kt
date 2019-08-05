package com.hazem.newslist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.hazem.newslist.R

class MainActivity :AppCompatActivity(),NavController.OnDestinationChangedListener{
    private lateinit var navHosFragment:NavHostFragment

    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHosFragment = supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        toolbar = findViewById(R.id.main_toolbar)

    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {

    }
}
