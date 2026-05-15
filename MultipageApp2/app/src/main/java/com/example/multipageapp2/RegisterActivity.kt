package com.example.multipageapp2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        val prefs           = getSharedPreferences("MangaPrefs", MODE_PRIVATE)
        val etUsername      = findViewById<EditText>(R.id.etRegUsername)
        val etPassword      = findViewById<EditText>(R.id.etRegPassword)
        val etConfirm       = findViewById<EditText>(R.id.etRegConfirm)
        val btnRegister     = findViewById<Button>(R.id.btnRegister)
        val btnBackToLogin  = findViewById<TextView>(R.id.txtBackToLogin)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirm  = etConfirm.text.toString().trim()

            when {
                username.isEmpty() ->
                    etUsername.error = "Enter a username"
                username.length < 3 ->
                    etUsername.error = "Username must be at least 3 characters"
                password.isEmpty() ->
                    etPassword.error = "Enter a password"
                password.length < 4 ->
                    etPassword.error = "Password must be at least 4 characters"
                confirm.isEmpty() ->
                    etConfirm.error = "Confirm your password"
                confirm != password ->
                    etConfirm.error = "Passwords do not match"
                prefs.contains("user_$username") ->
                    etUsername.error = "Username already taken"
                else -> {
                    // Save the account
                    prefs.edit()
                        .putString("user_$username", password)
                        .apply()

                    Toast.makeText(this, "Account created! Please log in.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }

        btnBackToLogin.setOnClickListener {
            finish()
        }
    }
}