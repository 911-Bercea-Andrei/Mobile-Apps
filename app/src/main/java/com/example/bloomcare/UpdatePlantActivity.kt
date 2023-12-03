package com.example.bloomcare

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class UpdatePlantActivity: AppCompatActivity() {

    var etOldName: String? = null
    lateinit var etName: EditText
    lateinit var etSpecies: EditText
    lateinit var etInterval: EditText
    lateinit var etLastWatered: EditText
    lateinit var etReminderTime: EditText
    lateinit var bttnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_plant)

        val colorDrawable = ColorDrawable(Color.parseColor("#FFFFFF"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        window.statusBarColor = ContextCompat.getColor(this@UpdatePlantActivity, R.color.fern_green)

        etName = findViewById(R.id.etvName)
        etSpecies = findViewById(R.id.etvSpecies)
        etInterval = findViewById(R.id.etvInterval)
        etLastWatered = findViewById(R.id.etvLastWatered)
        etReminderTime = findViewById(R.id.etvReminderTime)

        bttnSave = findViewById(R.id.bttnSave)

        val bundle = intent.extras
        if(bundle != null)
        {
            etName.hint = bundle.getString("nameUpdated")
            etOldName = bundle.getString("nameUpdated").toString()
            etSpecies.hint = bundle.getString("speciesUpdated")
            etInterval.hint = bundle.getString("intervalUpdated")
            etLastWatered.hint = bundle.getString("lastWateredUpdated")
            etReminderTime.hint = bundle.getString("reminderTimeUpdated")
        }

    bttnSave.setOnClickListener()
    {
        val newBundle = Bundle()
        newBundle.putString("oldName", etOldName)

        if(etName.text.isNotEmpty())
        {
            newBundle.putString("nameUpdated", etName.text.toString())
        }

        else
        {
            newBundle.putString("nameUpdated", etName.hint.toString())
        }

        if(etSpecies.text.isNotEmpty())
        {
            newBundle.putString("speciesUpdated", etSpecies.text.toString())
        }

        else
        {
            newBundle.putString("speciesUpdated", etSpecies.hint.toString())
        }

        if(etInterval.text.isNotEmpty())
        {
            newBundle.putString("intervalUpdated", etInterval.text.toString())
        }

        else
        {
            newBundle.putString("intervalUpdated", etInterval.hint.toString())
        }

        if(etLastWatered.text.isNotEmpty())
        {
            newBundle.putString("lastWateredUpdated", etLastWatered.text.toString())
        }

        else
        {
            newBundle.putString("lastWateredUpdated", etLastWatered.hint.toString())
        }

        if(etReminderTime.text.isNotEmpty())
        {
            newBundle.putString("reminderTimeUpdated", etReminderTime.text.toString())
        }

        else
        {
            newBundle.putString("reminderTimeUpdated", etReminderTime.hint.toString())
        }

        val intent = Intent(this, ListPlantsActivity::class.java)
        intent.putExtras(newBundle)
        startActivity(intent)
    }

    }

}

