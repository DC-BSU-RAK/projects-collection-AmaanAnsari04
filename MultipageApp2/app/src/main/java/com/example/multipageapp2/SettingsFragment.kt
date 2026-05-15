package com.example.multipageapp2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import android.widget.Button
import android.widget.Switch
import android.widget.TextView

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("MangaPrefs", Context.MODE_PRIVATE)

        val txtUsername    = view.findViewById<TextView>(R.id.txtUsername)
        val switchDarkMode = view.findViewById<Switch>(R.id.switchDarkMode)
        val switchRTL      = view.findViewById<Switch>(R.id.switchRTL)
        val btnLogout      = view.findViewById<Button>(R.id.btnLogout)

        txtUsername.text          = prefs.getString("username", "User")
        switchDarkMode.isChecked  = prefs.getBoolean("darkMode", true)
        switchRTL.isChecked       = prefs.getBoolean("rtlMode", false)

        switchDarkMode.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("darkMode", checked).apply()
        }
        switchRTL.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("rtlMode", checked).apply()
        }

        btnLogout.setOnClickListener {
            prefs.edit().putBoolean("isLoggedIn", false).apply()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }
}