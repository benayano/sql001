package com.example.sql001.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sql001.model.DataBaceRepository

class DBClassStudentFactory (val repository: DataBaceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass ==  MainViewModel::class.java) {
            return MainViewModel(repository) as T
        }else{
            throw IllegalAccessError()
        }
    }
}

