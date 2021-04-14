package com.carlostorres.virtusdigital.data.local.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.carlostorres.virtusdigital.data.local.dao.ItemsDao
import com.carlostorres.virtusdigital.data.local.entity.Items

class ItemsRepository(private val itemsDao: ItemsDao) {

    val allItems: LiveData<List<Items>> = itemsDao.getItems()

    @WorkerThread
    suspend fun insertItem(items: Items) {
        itemsDao.insertItem(items)
    }

    @WorkerThread
    suspend fun deleteItem(id: Int) {
        itemsDao.deleteItem(id)
    }

}