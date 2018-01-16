package pl.brolek.starwarsapp.main.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.brolek.starwarsapp.databinding.MainRecyclerLoadingItemBinding
import pl.brolek.starwarsapp.databinding.MainRecyclerPersonItemBinding
import pl.brolek.starwarsapp.databinding.MainRecyclerStarshipItemBinding
import pl.brolek.starwarsapp.databinding.MainRecyclerVehicleItemBinding
import pl.brolek.starwarsapp.main.RecyclerListListener
import pl.brolek.starwarsapp.main.data.MainModels
import javax.inject.Inject


/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
class MainRecyclerAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemsList: MutableList<MainModels.RecyclerItem> = mutableListOf()
    private val PERSON_VIEW_TYPE = 0
    private val VEHICLE_VIEW_TYPE = 1
    private val STARSHIP_VIEW_TYPE = 2
    private val LOADING_VIEW_TYPE = 3
    var listener: RecyclerListListener? = null
    private var actualPage = 1
    private var shouldLoadMore = true

    fun getActualPage(): Int = actualPage

    fun incrementActualPage() {
        this.actualPage++
    }

    fun resetParams() {
        this.actualPage = 1
        this.shouldLoadMore = true
        this.itemsList.clear()
        notifyDataSetChanged()
    }

    fun addElementsToList(itemsList: List<MainModels.RecyclerItem>) {

        this.itemsList = itemsList as MutableList<MainModels.RecyclerItem>
        notifyDataSetChanged()
    }

    fun getList(): List<MainModels.RecyclerItem> = itemsList

    fun appendToList(itemsList: List<MainModels.RecyclerItem>, shouldLoadMore: Boolean) {
        this.shouldLoadMore = shouldLoadMore
        for (item in itemsList) {
            this.itemsList.add(item)
            notifyItemRangeChanged(0, itemsList.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)

        return when (viewType) {
            PERSON_VIEW_TYPE -> {
                val view = MainRecyclerPersonItemBinding.inflate(inflater, parent, false)
                PeopleViewHolder(view)
            }
            VEHICLE_VIEW_TYPE -> {
                val view = MainRecyclerVehicleItemBinding.inflate(inflater, parent, false)
                VehicleViewHolder(view)
            }
            STARSHIP_VIEW_TYPE -> {
                val view = MainRecyclerStarshipItemBinding.inflate(inflater, parent, false)
                StarshipViewHolder(view)
            }
            else -> {
                val view = MainRecyclerLoadingItemBinding.inflate(inflater, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int): Int {

        return if (position == itemsList.size - 1 && shouldLoadMore)
            LOADING_VIEW_TYPE
        else if (itemsList[0] is MainModels.Person)
            PERSON_VIEW_TYPE
        else if (itemsList[0] is MainModels.Vehicle)
            VEHICLE_VIEW_TYPE
        else STARSHIP_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when {
            holder is PeopleViewHolder -> setDataToPersonElements(holder.view, position)
            holder is VehicleViewHolder -> setDataToVehicleElements(holder.view, position)
            holder is StarshipViewHolder -> setDataToStarshipElements(holder.view, position)
            shouldLoadMore -> listener?.onLoadMoreTriggered()
        }
    }

    private fun setDataToPersonElements(holder: MainRecyclerPersonItemBinding?, position: Int) {
        val personItem = itemsList[position] as MainModels.Person
        holder?.person = personItem
    }

    private fun setDataToVehicleElements(holder: MainRecyclerVehicleItemBinding?, position: Int) {
        val vehicleItem = itemsList[position] as MainModels.Vehicle
        holder?.vehicle = vehicleItem
    }

    private fun setDataToStarshipElements(holder: MainRecyclerStarshipItemBinding?, position: Int) {
        val starshipItem = itemsList[position] as MainModels.Starship
        holder?.starship = starshipItem
    }

    inner class PeopleViewHolder(var view: MainRecyclerPersonItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(person: MainModels.Person) {
            view.person = person
        }
    }

    inner class VehicleViewHolder(var view: MainRecyclerVehicleItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(vehicle: MainModels.Vehicle) {
            view.vehicle = vehicle
        }
    }

    inner class StarshipViewHolder(var view: MainRecyclerStarshipItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(starship: MainModels.Starship) {
            view.starship = starship
        }
    }

    inner class LoadingViewHolder(var view: MainRecyclerLoadingItemBinding) : RecyclerView.ViewHolder(view.root)
}