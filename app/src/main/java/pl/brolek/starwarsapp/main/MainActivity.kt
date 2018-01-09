package pl.brolek.starwarsapp.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import pl.brolek.starwarsapp.R
import pl.brolek.starwarsapp.app.App
import pl.brolek.starwarsapp.main.adapters.PeopleAdapter
import pl.brolek.starwarsapp.main.adapters.VehiclesAdapter
import pl.brolek.starwarsapp.main.data.MainModels
import pl.brolek.starwarsapp.main.di.MainComponent
import pl.brolek.starwarsapp.main.di.MainModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View, RecyclerListListener {

    private lateinit var mainComponent: MainComponent
    @Inject lateinit var presenter: MainContract.Presenter
    @Inject lateinit var peopleAdapter: PeopleAdapter
    @Inject lateinit var vehiclesAdapter: VehiclesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeInjection()
        setRecyclerParams()
        presenter.attachView(this)
        setProgressBarVisible(true)
        main_recycler.adapter = peopleAdapter
        presenter.getPeople(1, false)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun initializeInjection() {
        mainComponent = (applicationContext as App).appComponent.plus(MainModule(this))
        mainComponent.injectMainActivity(this)
    }

    private fun setRecyclerParams() {
        peopleAdapter.listener = this
        main_recycler.layoutManager = LinearLayoutManager(applicationContext)
        main_recycler.itemAnimator = DefaultItemAnimator()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_people -> {
                if (main_recycler.adapter !is PeopleAdapter) {
                    setProgressBarVisible(true)
                    main_recycler.adapter = peopleAdapter
                    peopleAdapter.resetParams()
                    presenter.getPeople(1, false)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_vehicles -> {
                if (main_recycler.adapter !is VehiclesAdapter) {
                    setProgressBarVisible(true)
                    main_recycler.adapter = vehiclesAdapter
                    presenter.getVehicles(1)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_starships -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setProgressBarVisible(visible: Boolean) {
        if (visible)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.GONE
    }

    override fun showPeople(peopleList: List<MainModels.Person>, shouldAppend: Boolean, shouldLoadMore: Boolean) {
        setProgressBarVisible(false)
        if (shouldAppend)
            peopleAdapter.appendToList(peopleList, shouldLoadMore)
        else
            peopleAdapter.addElementsToList(peopleList)
    }

    override fun showVehicles(vehiclesList: List<MainModels.Vehicle>) {
        setProgressBarVisible(false)
        vehiclesAdapter.addElementsToList(vehiclesList)
    }

    override fun showError(message: String) {
        setProgressBarVisible(false)
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun onLoadMoreTriggered() {
        if (main_recycler.adapter.itemCount > 0) {
            if (main_recycler.adapter is PeopleAdapter) {
                peopleAdapter.incrementActualPage()
                presenter.getPeople(peopleAdapter.getActualPage(), true)
            }
        }
    }


}
