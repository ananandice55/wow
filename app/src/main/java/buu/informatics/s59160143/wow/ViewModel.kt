package buu.informatics.s59160143.wow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import buu.informatics.s59160143.wow.database.PeopleDatabase
import buu.informatics.s59160143.wow.database.PeopleDatabaseModel
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: ViewModelFactory
    val peopleAll: LiveData<List<PeopleDatabaseModel>>

    init {
        val wordsDao = PeopleDatabase.getInstance(application.applicationContext).peopleDatabaseModels()
        repository = ViewModelFactory(wordsDao)
        peopleAll = repository.peopleAll

    }

    fun insert(word: PeopleDatabaseModel) = viewModelScope.launch {
        repository.insert(word)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }

    fun get() = viewModelScope.launch {
        repository.get()
    }


}