package com.carlostorres.virtusdigital.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.carlostorres.virtusdigital.data.local.entity.Items
import com.carlostorres.virtusdigital.data.local.repositories.ItemsRepository
import com.carlostorres.virtusdigital.data.local.room.ItemsRoomDatabase
import com.carlostorres.virtusdigital.data.network.repositories.SearchRepository
import com.carlostorres.virtusdigital.model.interfacs.HomeListener
import com.carlostorres.virtusdigital.ui.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM(application: Application): AndroidViewModel(application) {

    // Listener
    var listener: HomeListener ?= null

    // Database
    private val repository: ItemsRepository
    val allItems: LiveData<List<Items>>

    init {
        val dao = ItemsRoomDatabase.getDatabase(application).itemsDao()
        repository = ItemsRepository(dao)
        allItems = repository.allItems
    }


    fun getItemsInfo() {
        Coroutines.main {

            listener?.showProgressBar()

            val response = SearchRepository().getItems()
            if ( response.isSuccessful ) {
                var itemValue = response.body()?.hits!!

                var date: String
                var commentItm: String
                var titleItem: String
                var urlItem: String
                var item: Items

                itemValue.forEach {
                    commentItm = if ( !it.comment_text.isNullOrEmpty() ) {
                        it.comment_text
                    } else {
                        "N/A"
                    }

                    date = if ( !it.created_at.isNullOrEmpty() ) {
                        it.created_at.split("T")[0]
                    } else {
                        "N/A"
                    }

                    titleItem = if ( !it.story_title.isNullOrEmpty()  ) {
                        it.story_title
                    } else if ( it.title == null ) {
                        "N/A"
                    } else {
                        it.title.toString()
                    }

                    urlItem = if ( !it.story_url.isNullOrEmpty()  ) {
                        it.story_url
                    } else if ( it.url != null ) {
                        it.url.toString()
                    } else {
                        "N/A"
                    }
                    item = Items(
                        0,
                        it.author,
                        commentItm,
                        date,
                        titleItem,
                        urlItem,
                    )

                    insertItems(item)
                }
            } else {
                listener?.onError()
            }
            listener?.hideProgressBar()
        }

    }
    
    private fun insertItems(items: Items) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertItem(items)
    }

    fun deleteItem(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteItem(id)
    }
}