package de.syntaxinstitut.android_ww_template.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.android_ww_template.data.AppRepository
import de.syntaxinstitut.android_ww_template.data.datamodels.Character
import de.syntaxinstitut.android_ww_template.data.local.getDatabase
import de.syntaxinstitut.android_ww_template.data.remote.HarryPotterApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val repository = AppRepository(HarryPotterApi, database)

    val characters = repository.characters
    // value für den BottomNav-Toggle
    var _showBottomNav = MutableLiveData(true)
    val showBottomNav: LiveData<Boolean>
        get() = _showBottomNav

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters()
        }
    }

    fun switchVisibleBottomNav() {
        _showBottomNav.value = _showBottomNav.value?.not()
    }


    fun getAllLiked(): LiveData<List<Character>> {
        return repository.getAllLiked()
    }

    fun getById(id: String): LiveData<Character> {
        return repository.getCatById(id)
    }

    fun updateLike(liked: Int, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLike(liked, id)
        }

    }


}