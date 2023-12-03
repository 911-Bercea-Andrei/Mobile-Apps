package com.example.bloomcare

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.plant_item.view.*
import kotlinx.android.synthetic.main.plant_item.view.textName
import kotlinx.android.synthetic.main.plant_item.view.textInterval
import kotlinx.android.synthetic.main.plant_item.view.textSpecies

class PlantAdapter () : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>()
{
    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var plants : MutableList<Plant> = mutableListOf()

    init {
        populatePlants()
    }


    private fun populatePlants()
    {
        plants.add(Plant("Aloe Vera", "Succulent", "10 days", "10.10.2023", "3"))
        plants.add(Plant("Banana Tree", "Tropical tree", "6 days", "10.10.2023", "5"))
        plants.add(Plant("Snake Plant", "Succulent", "8 days", "12.10.2023", "3"))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.plant_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plants[position]
        holder.itemView.apply {
            textName.text = currentPlant.name
            textSpecies.text = currentPlant.species
            textInterval.text = currentPlant.interval
        }

        holder.itemView.bttnEdit.setOnClickListener()
        {
            val bundle = Bundle()
            bundle.putString("nameUpdate", currentPlant.name)
            bundle.putString("speciesUpdate", currentPlant.species)
            bundle.putString("intervalUpdate", currentPlant.interval)
            bundle.putString("lastWateredUpdate", currentPlant.lastWatered)
            bundle.putString("reminderTimeUpdate", currentPlant.reminderTime)

            val intent = Intent(holder.itemView.rootView.context, UpdatePlantActivity::class.java)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.bttnDelete.setOnClickListener()
        {
            val alertBuilder = AlertDialog.Builder(holder.itemView.rootView.context)

            alertBuilder.setTitle("Do you want to delete this plant?")
            alertBuilder.setMessage("To delete press OK")

            alertBuilder.setNegativeButton("CANCEL"){ dialog, _ ->
                dialog.cancel()
            }.setPositiveButton("OK") { _, _ ->
                deletePlant(position)
//                deletePlantItem(position)
            }
            alertBuilder.show()
        }
    }

    fun addPlant(plant: Plant)
    {
        plants.add(plant)
        notifyDataSetChanged()

    }

    private fun deletePlant(position: Int)
    {
        plants.removeAt(position)
        notifyDataSetChanged()
    }

    fun updatePlant(oldName: String, newPlant: Plant)
    {
        var i: Int = -1
        for (plantItems in plants)
        {
            i ++
            if (plantItems.name == oldName)
            {
                plants[i] = newPlant
                break
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return plants.size
    }


}