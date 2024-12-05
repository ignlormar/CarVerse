package com.ignacio.carverse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ignacio.carverse.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val PREFS_NAME = "UserPrefs"
    private val KEY_FIRST_TIME = "sp_first_time"
    private val KEY_USERNAME = "username"
    private val KEY_PASSWORD = "password"
    private val KEY_REMEMBER = "remember"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bgLogin("https://images.squarespace-cdn.com/content/v1/5d7b795d1d74f17f87f61893/1627562017606-8695YEE8TX163BHRP7XH/1563796558-huge.jpg")

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editor = preferences.edit()

        if (preferences.getBoolean(KEY_REMEMBER, false)) {
            binding.etUsuario.setText(preferences.getString(KEY_USERNAME, ""))
            binding.etPassword.setText(preferences.getString(KEY_PASSWORD, ""))
            binding.cbRecordar.isChecked = true
        }

        binding.registerBtn.setOnClickListener {
            val username = binding.etUsuario.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                    "Ambos campos son obligatorios.",
                    Toast.LENGTH_SHORT)
                    .show()

                return@setOnClickListener
            }

            val savedUsername = preferences.getString(KEY_USERNAME, null)

            if (savedUsername == username){
                Toast.makeText(this,
                    "Este usuario ya existe.",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                editor.putString(KEY_USERNAME, username).apply()
                editor.putString(KEY_PASSWORD, password).apply()
                Toast.makeText(this,
                    "Registro exitoso.",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.loginBtn.setOnClickListener {
            val username = binding.etUsuario.text.toString()
            val password = binding.etPassword.text.toString()
            val savedUsername = preferences.getString(KEY_USERNAME, null)
            val savedPassword = preferences.getString(KEY_PASSWORD, null)

            if (username == savedUsername && password == savedPassword) {
                Toast.makeText(this,
                    "Login exitoso.",
                    Toast.LENGTH_SHORT)
                    .show()
                navigateToMainActivity()

                editor.putBoolean(KEY_REMEMBER, binding.cbRecordar.isChecked).apply()
            } else {
                Toast.makeText(this,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }



    }

    private fun bgLogin(url: String){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.loginBg)
    }

    private fun navigateToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}