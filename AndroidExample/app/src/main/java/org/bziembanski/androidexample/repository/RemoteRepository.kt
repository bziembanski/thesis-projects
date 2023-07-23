package org.bziembanski.androidexample.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.bziembanski.androidexample.data.PeopleResult
import org.bziembanski.androidexample.data.Person
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

class RemoteRepository {
    companion object {
        private const val baseUrl = "https://swapi.dev/api/"
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .baseUrl(baseUrl)
        .build()

    private val personService = retrofit.create(PersonApiService::class.java)

    suspend fun fetchAll(): List<Person> {
        return personService.fetchAll().results
    }
}

interface PersonApiService {
    @GET("people")
    suspend fun fetchAll(): PeopleResult
}