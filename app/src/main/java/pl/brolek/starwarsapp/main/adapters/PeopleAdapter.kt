package pl.brolek.starwarsapp.main.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.brolek.starwarsapp.databinding.MainRecyclerLoadingItemBinding
import pl.brolek.starwarsapp.databinding.MainRecyclerPersonItemBinding
import pl.brolek.starwarsapp.main.RecyclerListListener
import pl.brolek.starwarsapp.main.data.MainModels
import javax.inject.Inject


/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
class PeopleAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var peopleList: MutableList<MainModels.Person> = mutableListOf()
    private val ITEM_VIEW_TYPE = 0
    private val LOADING_VIEW_TYPE = 1
    var listener: RecyclerListListener? = null
    private var actualPage = 1
    private var shouldLoadMore = true

    fun getActualPage(): Int = actualPage

    fun incrementActualPage() {
        this.actualPage++
    }

    fun resetParams() {
        this.actualPage = 0
        this.shouldLoadMore = true
    }

    fun addElementsToList(peopleList: List<MainModels.Person>) {
        this.peopleList = peopleList as MutableList<MainModels.Person>
        notifyDataSetChanged()
    }

    fun appendToList(peopleList: List<MainModels.Person>, shouldLoadMore: Boolean) {
        this.shouldLoadMore = shouldLoadMore
        for (person in peopleList) {
            this.peopleList.add(person)
            notifyItemRangeChanged(0, peopleList.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)

        return if (viewType == ITEM_VIEW_TYPE) {
            val view = MainRecyclerPersonItemBinding.inflate(inflater, parent, false)
            PeopleViewHolder(view)
        } else {
            val view = MainRecyclerLoadingItemBinding.inflate(inflater, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int = peopleList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == peopleList.size - 1 && shouldLoadMore) LOADING_VIEW_TYPE else ITEM_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is PeopleViewHolder)
            setDataToElements(holder.view, position)
        else if (shouldLoadMore)
            listener?.onLoadMoreTriggered()

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

    inner class LoadingViewHolder(var view: MainRecyclerLoadingItemBinding) : RecyclerView.ViewHolder(view.root) {

    }
}