package com.carlostorres.virtusdigital.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carlostorres.virtusdigital.data.local.entity.Items

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items_table")
    fun getItems(): LiveData<List<Items>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(items: Items)

    @Query("DELETE FROM items_table WHERE id = :id")
    fun deleteItem(id: Int)
}