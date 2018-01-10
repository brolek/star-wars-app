package pl.brolek.starwarsapp.main.data


/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
class MainModels {

    data class PersonResult(val count: Int, val next: String, val previous: Any, val results: List<Person>)

    data class VehicleResult(val count: Int, val next: String, val previous: Any, val results: List<Vehicle>)

    abstract class RecyclerItem

    data class Person(val name: String, val height: String, val mass: String, val hair_color: String,
                      val skin_color: String, val eye_color: String, val birth_year: String, val gender: String,
                      val homeworld: String, val films: List<String>, val species: List<String>,
                      val vehicles: List<String>, val starships: List<String>, val created: String,
                      val edited: String, val url: String) : RecyclerItem()

    data class Vehicle(val name: String, val model: String, val manufacturer: String, val cost_in_credits: String,
                       val length: String, val max_atmosphering_speed: String, val crew: String,
                       val passengers: String, val cargo_capacity: String, val consumables: String,
                       val vehicle_class: String, val pilots: List<String>, val films: List<String>,
                       val created: String, val edited: String, val url: String) : RecyclerItem()

}