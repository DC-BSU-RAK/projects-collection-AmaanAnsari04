package com.example.multipageapp2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MangaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga_detail)

        // Get data passed from HomeFragment
        val title    = intent.getStringExtra("title") ?: ""
        val imageUrl = intent.getStringExtra("imageUrl") ?: ""
        val summary  = intent.getStringExtra("summary") ?: ""
        val malUrl   = intent.getStringExtra("malUrl") ?: ""
        val genre    = intent.getStringExtra("genre") ?: ""

        // Bind views
        findViewById<TextView>(R.id.detailTitle).text   = title
        findViewById<TextView>(R.id.detailGenre).text   = genre
        findViewById<TextView>(R.id.detailSummary).text = summary

        // Load cover image
        Glide.with(this)
            .load(imageUrl)
            .placeholder(android.R.color.darker_gray)
            .into(findViewById(R.id.detailImage))

        // Back button
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Open MAL page in browser
        findViewById<Button>(R.id.btnMAL).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(malUrl))
            startActivity(intent)
        }

        // Save / unsave toggle
        val prefs = getSharedPreferences("MangaPrefs", MODE_PRIVATE)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val saved = prefs.getStringSet("collection", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Show current state
        btnSave.text = if (title in saved) "♥ Saved" else "♡ Save to Collection"

        btnSave.setOnClickListener {
            val current = prefs.getStringSet("collection", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
            if (title in current) {
                current.remove(title)
                btnSave.text = "♡ Save to Collection"
            } else {
                current.add(title)
                btnSave.text = "♥ Saved"
            }
            prefs.edit().putStringSet("collection", current).apply()
        }
    }
}