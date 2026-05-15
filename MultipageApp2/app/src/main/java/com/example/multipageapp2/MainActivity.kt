package com.example.multipageapp2

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        navView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home       -> HomeFragment()
                R.id.nav_collection -> CollectionFragment()
                R.id.nav_settings   -> SettingsFragment()
                else                -> return@setOnItemSelectedListener false
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            true
        }

        findViewById<ImageButton>(R.id.btnInfo).setOnClickListener {
            InfoModalFragment().show(supportFragmentManager, "InfoModal")
        }
    }
}