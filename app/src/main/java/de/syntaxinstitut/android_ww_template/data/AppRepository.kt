package de.syntaxinstitut.android_ww_template.data

import android.util.Log
import androidx.lifecycle.LiveData
import de.syntaxinstitut.android_ww_template.data.datamodels.Character
import de.syntaxinstitut.android_ww_template.data.local.HarryPotterDatabase
import de.syntaxinstitut.android_ww_template.data.remote.HarryPotterApi
import java.lang.Exception

const val TAG = "AppRepositoryTAG"

class AppRepository(private val api: HarryPotterApi, private val database: HarryPotterDatabase) {

    val characters = database.HarryPotterDatabaseDao.getAll()

    suspend fun getCharacters() {
        try {

            var characterData = api.retrofitService.getCharacter()
            database.HarryPotterDatabaseDao.insertAll(characterData)

        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }

//    fun getCharacterById(id: String): Character {
//        return api.retrofitService.getCharacterById(id)
//    }

    fun getCatById(id: String) : LiveData<Character>{
        return database.HarryPotterDatabaseDao.getById(id)
    }
}