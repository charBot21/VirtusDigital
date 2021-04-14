package com.carlostorres.virtusdigital.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class Items(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "item_author")
    val author: String,
    @ColumnInfo(name = "item_comment_text")
    val comment_text: String,
    @ColumnInfo(name = "item_created_at")
    val created_at: String,
    @ColumnInfo(name = "item_title")
    val title: String,
    @ColumnInfo(name = "item_url")
    val url: String
)