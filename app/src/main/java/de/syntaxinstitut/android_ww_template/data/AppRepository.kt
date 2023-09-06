package de.syntaxinstitut.android_ww_template.data

import android.util.Log
import androidx.lifecycle.LiveData
import de.syntaxinstitut.android_ww_template.R
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
            for (character in characterData) {
                if (character.image.isBlank()){
                    character.image = "https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"
                }
            }

            if (database.HarryPotterDatabaseDao.count() == 0){

                database.HarryPotterDatabaseDao.insertAll(characterData)
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }

    }

    fun getAllLiked(): LiveData<List<Character>>{
        return database.HarryPotterDatabaseDao.getAllLiked()

    }

    fun getCatById(id: String) : LiveData<Character>{
        return database.HarryPotterDatabaseDao.getById(id)
    }

    fun updateLike(liked: Int,id: String){
        try {
            database.HarryPotterDatabaseDao.updateLike(liked,id)
        }catch (e: Exception){
            Log.e(TAG, "Error update Like: $e")
        }
    }
}