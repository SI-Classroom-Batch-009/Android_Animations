package de.syntaxinstitut.android_ww_template.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntaxinstitut.android_ww_template.data.datamodels.Character


@Database(entities = [Character::class], version = 1)
abstract class HarryPotterDatabase : RoomDatabase() {

    abstract val HarryPotterDatabaseDao: HarryPotterDatabaseDao

}

private lateinit var INSTANCE: HarryPotterDatabase

fun getDatabase(context: Context): HarryPotterDatabase {
    synchronized(HarryPotterDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                HarryPotterDatabase::class.java,
                "harryPotter_databse"
            ).build()
        }
    }
    return INSTANCE
}