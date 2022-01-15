package com.example.moodle.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moodle.Initializer
import com.example.moodle.R
import com.example.moodle.extensions.gone
import com.example.moodle.extensions.show
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

    //Call this functions from any child fragment of MainActivity to toggle the visibility of the progress bar.
    fun handleProgressBar(isLoading: Boolean) {
        if(isLoading) {
            main_activity_progress_bar.show()
        } else {
            main_activity_progress_bar.gone()
        }
    }

}