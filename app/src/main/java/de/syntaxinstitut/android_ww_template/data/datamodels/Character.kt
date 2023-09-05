package de.syntaxinstitut.android_ww_template.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey
    var id: String,
    var name: String,
    var species: String,
    var gender: String,
    var house: String,
    var dateOfBirth: String?,
    var image: String,

)