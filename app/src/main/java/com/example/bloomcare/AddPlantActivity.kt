package com.example.bloomcare

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bloomcare.R

class AddPlantActivity: AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etSpecies: EditText
    lateinit var etInterval: EditText
    lateinit var etLastWatered: EditText
    lateinit var etReminderTime: EditText
    lateinit var bttnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)

        val colorDrawable = ColorDrawable(Color.parseColor("#FFFFFF"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        window.statusBarColor = ContextCompat.getColor(this@AddPlantActivity, R.color.fern_green)

        etName = findViewById(R.id.etvName)
        etSpecies = findViewById(R.id.etvSpecies)
        etInterval = findViewById(R.id.etvInterval)
        etLastWatered = findViewById(R.id.etvLastWatered)
        etReminderTime = findViewById(R.id.etvReminderTime)

        bttnSave = findViewById(R.id.bttnSave)

        bttnSave.setOnClickListener() {
            if (etName.text.isNotEmpty() and etSpecies.text.isNotEmpty()
                and etInterval.text.isNotEmpty() and etLastWatered.text.isNotEmpty()
                and etReminderTime.text.isNotEmpty())
            {
                val bundle = Bundle()
                bundle.putString("name", etName.text.toString())
                bundle.putString("species", etSpecies.text.toString())
                bundle.putString("interval", etInterval.text.toString())
                bundle.putString("lastWatered", etLastWatered.text.toString())
                bundle.putString("reminderTime", etReminderTime.text.toString())

                val intent = Intent(this, ListPlantsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            } else {
                val alertBuilder = AlertDialog.Builder(this)

                alertBuilder.setTitle("Add a plant")
                alertBuilder.setMessage("Fill in all the fields!")

                alertBuilder.setPositiveButton("OK") { dialog, _ ->
                    dialog.cancel()
                }
                alertBuilder.show()
            }
        }
    }
}