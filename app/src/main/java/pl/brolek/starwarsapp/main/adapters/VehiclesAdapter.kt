package pl.brolek.starwarsapp.main.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.brolek.starwarsapp.databinding.MainRecyclerVehicleItemBinding
import pl.brolek.starwarsapp.main.data.MainModels
import javax.inject.Inject

/**
 * Created by Bartłomiej Rolek on 2018-01-09
 */
class VehiclesAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<VehiclesAdapter.VehiclesViewHolder>() {

    private var vehiclesList: List<MainModels.Vehicle> = mutableListOf()

    fun addElementsToList(vehiclesList: List<MainModels.Vehicle>) {
        this.vehiclesList = vehiclesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VehiclesViewHolder {
        val view = MainRecyclerVehicleItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return VehiclesViewHolder(view)
    }

    override fun getItemCount(): Int = vehiclesList.size

    override fun onBindViewHolder(holder: VehiclesViewHolder?, position: Int) {
        setDataToElements(holder?.view, position)
    }

    private fun setDataToElements(holder: MainRecyclerVehicleItemBinding?, position: Int) {
        val vehicleItem = vehiclesList[position]
        holder?.vehicle = vehicleItem
    }

    inner class VehiclesViewHolder(var view: MainRecyclerVehicleItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(vehicle: MainModels.Vehicle) {
            view.vehicle = vehicle
        }
    }
}