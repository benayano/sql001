package com.example.sql001.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sql001.model.DataBaceRepository
import com.example.sql001.model.db.TodoItem

class MainViewModel(private val repository: DataBaceRepository) : ViewModel() {

    private var studentsViewLiveData = MutableLiveData<List<StudentViewData>>()
    fun bindListStudent(): LiveData<List<StudentViewData>> = studentsViewLiveData

    fun creatStudent(name: String) {
        repository.insertItems(convertToItem(StudentViewData(null, name, true)))
        loadStudents()
    }

    fun loadStudents() {
        studentsViewLiveData.postValue(convertToListStudent(repository.getAllTodos()))
    }

    fun remoovStudent(studentViewData: StudentViewData) {
        repository.delete(convertToItem(studentViewData))
        loadStudents()
    }

    fun updatStudent(studentViewData: StudentViewData) =
        repository.updateOrCreate(convertToItem(studentViewData))

    fun loadSelectedStudent() = convertToListStudent(repository.findUnCompleteTodos())

    //convertion
    fun convertToItem(studentViewData: StudentViewData) = TodoItem(
        id = studentViewData.id,
        name = studentViewData.name,
        isCompleted = studentViewData.isStudent
    )

    fun convertToStudent(item: TodoItem) = StudentViewData(item.id, item.name, item.isCompleted)
    fun convertToListStudent(listItem: List<TodoItem>): List<StudentViewData> {
        return listItem.map {
            StudentViewData(
                id = it.id,
                name = it.name,
                isStudent = it.isCompleted
            )
        }
    }

    fun onStatusChang(studentViewData: StudentViewData) {
        if (studentViewData.isStudent) {
            repository.updateOrCreate(convertToItem(studentViewData.copy(isStudent = false)))
        }else{
         repository.updateOrCreate(convertToItem(studentViewData).copy(isCompleted = true))
        }
    }
}