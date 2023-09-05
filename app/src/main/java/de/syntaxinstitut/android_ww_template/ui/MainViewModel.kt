package de.syntaxinstitut.android_ww_template.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.android_ww_template.data.AppRepository
import de.syntaxinstitut.android_ww_template.data.local.getDatabase
import de.syntaxinstitut.android_ww_template.data.remote.HarryPotterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val repository = AppRepository(HarryPotterApi, database)

    val characters = repository.characters

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters()
        }
    }
}