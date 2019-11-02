package buu.informatics.s59160143.wow

import androidx.lifecycle.LiveData
import buu.informatics.s59160143.wow.database.PeopleDao
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel

class ViewModelFactory(private val Dao: PeopleDao) {

    val allWater: LiveData<List<PeopleDatabaseModel>> = Dao.get()

    fun insert(water: PeopleDatabaseModel) {
        Dao.insert(water)
    }

    fun clear() {
        Dao.clear()
    }
    fun get (){
        Dao.get()
    }

}