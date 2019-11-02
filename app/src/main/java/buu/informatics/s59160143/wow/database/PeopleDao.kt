package buu.informatics.s59160143.wow.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeopleDao {

    @Insert
    fun insert(water: PeopleDatabaseModel)

    @Update
    fun update(water: PeopleDatabaseModel)

    @Query("SELECT * FROM people ORDER BY peopleId ASC")
    fun get(): LiveData<List<PeopleDatabaseModel>>

    @Query("DELETE FROM people")

    fun clear()

}