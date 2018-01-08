package pl.brolek.starwarsapp.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.brolek.starwarsapp.databinding.MainRecyclerPersonItemBinding
import pl.brolek.starwarsapp.main.data.MainModels
import javax.inject.Inject

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
class PeopleAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var peopleList: List<MainModels.Person> = mutableListOf()

    fun addElementsToList(peopleList: List<MainModels.Person>) {
        this.peopleList = peopleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PeopleViewHolder {
        val view = MainRecyclerPersonItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return PeopleViewHolder(view)
    }

    override fun getItemCount(): Int = peopleList.size

    override fun onBindViewHolder(holder: PeopleViewHolder?, position: Int) {
        setDataToElements(holder?.view, position)
    }

    private fun setDataToElements(holder: MainRecyclerPersonItemBinding?, position: Int) {
        val personItem = peopleList[position]
        holder?.person = personItem
    }

    inner class PeopleViewHolder(var view: MainRecyclerPersonItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(person: MainModels.Person) {
            view.person = person
        }
    }
}