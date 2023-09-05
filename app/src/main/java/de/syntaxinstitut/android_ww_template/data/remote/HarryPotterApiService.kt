package de.syntaxinstitut.android_ww_template.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.android_ww_template.data.datamodels.Character
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Die Konstante enthält die URL der API
const val BASE_URL = "https://hp-api.onrender.com/api/"


// Moshi konvertiert Serverantworten in Kotlin Objekte
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit übernimmt die Kommunikation und übersetzt die Antwort
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HarryPotterApiService {

    @GET("characters")
    suspend fun getCharacter(): List<Character>
}

object HarryPotterApi {
    val retrofitService: HarryPotterApiService by lazy { retrofit.create(HarryPotterApiService::class.java) }
}
