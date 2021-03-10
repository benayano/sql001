package com.example.sql001.model

import com.example.sql001.model.db.TodoDao
import com.example.sql001.model.db.TodoItem

class DataBaceRepository (private val todoDao: TodoDao){

    fun insertItems(item : TodoItem)= todoDao.insertItems(item )
    fun updateOrCreate(item : TodoItem) {

         todoDao.updateOrCreate(item )
    }
    fun delete(item: TodoItem) = todoDao.delete(item)

    fun findUnCompleteTodos():List<TodoItem> = todoDao.findUnCompleteTodos()

    fun getAllTodos():List<TodoItem> = todoDao.getAllTodos()
}