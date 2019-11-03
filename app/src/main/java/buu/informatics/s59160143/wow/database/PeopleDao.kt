package buu.informatics.s59160143.wow.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeopleDao {

    @Insert
    fun insert(people: PeopleDatabaseModel)

    @Update
    fun update(people: PeopleDatabaseModel)

    @Query("SELECT * FROM people ORDER BY name ASC")
    fun get(): LiveData<List<PeopleDatabaseModel>>

    @Query("DELETE FROM people")

    fun clear()

}