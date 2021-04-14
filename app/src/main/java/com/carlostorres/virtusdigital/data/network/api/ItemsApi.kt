package com.carlostorres.virtusdigital.data.network.api

import com.carlostorres.virtusdigital.data.network.models.reponses.SearchResponse
import com.carlostorres.virtusdigital.model.constants.Environment
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ItemsApi {

    @GET(Environment.SEARCH_DATA)
    suspend fun getItems():Response<SearchResponse>

    companion object {
        operator fun invoke(): ItemsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Environment.URL_BASE)
                .build()
                .create(ItemsApi::class.java)
        }
    }
}