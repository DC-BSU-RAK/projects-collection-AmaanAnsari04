package com.example.multipageapp2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        val prefs         = getSharedPreferences("MangaPrefs", MODE_PRIVATE)

        if (prefs.getBoolean("isLoggedIn", false)) {
            goToMain()
            return
        }

        val etUsername    = findViewById<EditText>(R.id.etUsername)
        val etPassword    = findViewById<EditText>(R.id.etPassword)
        val btnLogin      = findViewById<Button>(R.id.btnLogin)
        val btnGoRegister = findViewById<TextView>(R.id.txtGoRegister)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                username.isEmpty() ->
                    etUsername.error = "Enter your username"
                password.isEmpty() ->
                    etPassword.error = "Enter your password"
                !prefs.contains("user_$username") ->
                    etUsername.error = "No account found with that username"
                prefs.getString("user_$username", "") != password ->
                    etPassword.error = "Incorrect password"
                else -> {
                    prefs.edit()
                        .putBoolean("isLoggedIn", true)
                        .putString("username", username)
                        .apply()
                    goToMain()
                }
            }
        }

        btnGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}