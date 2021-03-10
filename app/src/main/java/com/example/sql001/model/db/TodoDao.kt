package com.example.sql001.model.db

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertItems(item : TodoItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrCreate(Item : TodoItem)

    @Delete
    fun delete(item: TodoItem)

    @Query( "SELECT * FROM todoitem WHERE isCompleted ")
    fun findUnCompleteTodos():List<TodoItem>

    @Query("SELECT * FROM todoitem")
    fun getAllTodos():List<TodoItem>


}