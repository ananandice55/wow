package buu.informatics.s59160143.wow

import androidx.lifecycle.LiveData
import buu.informatics.s59160143.wow.database.PeopleDao
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel

class ViewModelFactory(private val Dao: PeopleDao) {

    val peopleAll: LiveData<List<PeopleDatabaseModel>> = Dao.get()

    fun insert(people: PeopleDatabaseModel) {
        Dao.insert(people)
    }

    fun clear() {
        Dao.clear()
    }
    fun get (){
        Dao.get()
    }

}