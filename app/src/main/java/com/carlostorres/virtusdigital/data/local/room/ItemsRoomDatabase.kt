package com.carlostorres.virtusdigital.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlostorres.virtusdigital.data.local.dao.ItemsDao
import com.carlostorres.virtusdigital.data.local.entity.Items

@Database(entities = [Items::class], version = 1)
abstract class ItemsRoomDatabase: RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

    companion object {
        @Volatile
        private var INSTANCE: ItemsRoomDatabase ?= null

        fun getDatabase(context: Context): ItemsRoomDatabase {
            val tmpInstance = INSTANCE

            if ( tmpInstance != null ) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemsRoomDatabase::class.java,
                        "items_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }

}