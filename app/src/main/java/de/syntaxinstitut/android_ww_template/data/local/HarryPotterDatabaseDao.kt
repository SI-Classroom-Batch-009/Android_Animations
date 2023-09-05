package de.syntaxinstitut.android_ww_template.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.syntaxinstitut.android_ww_template.data.datamodels.Character

@Dao
interface HarryPotterDatabaseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Character>)

    @Query("SELECT * FROM character_table")
    fun getAll(): LiveData<List<Character>>


}