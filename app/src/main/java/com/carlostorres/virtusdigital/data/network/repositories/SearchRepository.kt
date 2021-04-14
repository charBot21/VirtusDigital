package com.carlostorres.virtusdigital.data.network.repositories

import com.carlostorres.virtusdigital.data.network.api.ItemsApi
import com.carlostorres.virtusdigital.data.network.models.reponses.SearchResponse
import retrofit2.Response

class SearchRepository() {

    suspend fun getItems(): Response<SearchResponse> {
        return  ItemsApi().getItems()
    }

}