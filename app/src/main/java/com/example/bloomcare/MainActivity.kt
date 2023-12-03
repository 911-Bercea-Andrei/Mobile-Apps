package com.example.bloomcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_plants)

        supportActionBar?.hide()

        val window: Window = this@MainActivity.window

        window.statusBarColor =
            ContextCompat.getColor(this@MainActivity, R.color.bg_grey)

            val intent = Intent(this, ListPlantsActivity::class.java)
            startActivity(intent)

    }
}