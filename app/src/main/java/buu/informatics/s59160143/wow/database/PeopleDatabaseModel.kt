package buu.informatics.s59160143.wow.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PeopleDatabaseModel(
    //@ColumnInfo(name = "picture")
    //var peoplePicture: String,
    @PrimaryKey
    @ColumnInfo(name = "name")
    var peopleName: String,

    @ColumnInfo(name = "age")
    var peopleAge: String,

    @ColumnInfo(name = "proportion")
    var peopleProportion: String,

    @ColumnInfo(name = "place")
    var peoplePlace: String,

    @ColumnInfo(name = "price")
    var peoplePrice: String,

    @ColumnInfo(name = "contact")
    var peopleContact: String,

    @ColumnInfo(name = "image")
    var peopleImage: String


)