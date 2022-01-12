package com.example.moodle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moodle.Initializer
import com.example.moodle.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.ma_fragmentContainer_navHost) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Initializer.init(application) //TODO: Tuval said to do so
        setContentView(R.layout.activity_main)

        //attach navController to bottom navigation
        ma_bottom_nav.setupWithNavController(navController)
    }

}