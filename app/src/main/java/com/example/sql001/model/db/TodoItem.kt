package com.example.sql001.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem (
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val name:String,
    val isCompleted:Boolean
)