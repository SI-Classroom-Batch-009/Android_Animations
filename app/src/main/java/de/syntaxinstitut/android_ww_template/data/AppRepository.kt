package de.syntaxinstitut.android_ww_template.data

import android.util.Log
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
}