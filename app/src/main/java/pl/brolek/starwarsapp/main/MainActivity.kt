package pl.brolek.starwarsapp.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import pl.brolek.starwarsapp.R
import pl.brolek.starwarsapp.app.App
import pl.brolek.starwarsapp.main.data.MainModels
import pl.brolek.starwarsapp.main.di.MainComponent
import pl.brolek.starwarsapp.main.di.MainModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mainComponent: MainComponent
    @Inject lateinit var presenter: MainContract.Presenter
    @Inject lateinit var peopleAdapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeInjection()
        setRecyclerParams()
        presenter.attachView(this)
        presenter.getPeople()

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
        main_recycler.layoutManager = LinearLayoutManager(applicationContext)
        main_recycler.itemAnimator = DefaultItemAnimator()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_people -> {
//                presenter.getPeople()
//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_vehicles -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_starships -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun showPeople(peopleList: List<MainModels.Person>) {
        peopleAdapter.addElementsToList(peopleList)
        main_recycler.adapter = peopleAdapter
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }


}
