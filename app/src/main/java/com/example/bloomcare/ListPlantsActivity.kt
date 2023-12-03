package com.example.bloomcare

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomcare.R
import kotlinx.android.synthetic.main.activity_list_plants.*

class ListPlantsActivity: AppCompatActivity() {
    private lateinit var plantAdapter: PlantAdapter

    var oldName: String? = null
    var name: String? = null
    var species: String? = null
    var interval: String? = null
    var lastWatered: String? = null
    var reminderTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_plants)

        val colorDrawable = ColorDrawable(Color.parseColor("#FFFFFF"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        window.statusBarColor = ContextCompat.getColor(this@ListPlantsActivity, R.color.fern_green)

        plantAdapter = PlantAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.rv_plants_list)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                layoutManager.orientation
            )
        )

        rv_plants_list.adapter = plantAdapter
        rv_plants_list.layoutManager = LinearLayoutManager(this)
        val buttonAddPlant = findViewById<Button>(R.id.bttn_plant_add)
        buttonAddPlant.setOnClickListener(){
            val intent = Intent(this, AddPlantActivity::class.java)
            startActivity(intent)
        }

        val bundle = intent.extras
        if(bundle != null)
        {
            if(bundle.getString("name") != null)
            {
                name = bundle.getString("name")
                species = bundle.getString("species")
                interval = bundle.getString("interval")
                lastWatered = bundle.getString("lastWatered")
                reminderTime = bundle.getString("reminderTime")

                val plant = Plant(name.toString(), species.toString(), interval.toString(),
                    lastWatered.toString(), reminderTime.toString())
                plantAdapter.addPlant(plant)
//                plantAdapter.addPlantItem(plant)
            }

            else if (bundle.getString("oldname") != null)
            {
                oldName = bundle.getString("oldname")
                name = bundle.getString("nameUpdated")
                species = bundle.getString("speciesUpdated")
                // speciesNumberUpdated??
                interval = bundle.getString("intervalUpdated")
                lastWatered = bundle.getString("lastWateredUpdated")
                reminderTime = bundle.getString("reminderTimeUpdated")

                val plant = Plant(name.toString(), species.toString(), interval.toString(),
                    lastWatered.toString(), reminderTime.toString())
                plantAdapter.updatePlant(oldName.toString(),plant)
            }
        }
    }

}