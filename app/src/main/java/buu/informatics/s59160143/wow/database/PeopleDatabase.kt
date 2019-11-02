package buu.informatics.s59160143.wow.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PeopleDatabaseModel::class], version = 2, exportSchema = false)

abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDatabaseModels(): PeopleDao

    companion object {

        @Volatile
        private var INSTANCE: PeopleDatabase? = null

        fun getInstance(context: Context): PeopleDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleDatabase::class.java,
                    "water_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance

            }
        }
    }

}