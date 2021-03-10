package com.example.sql001.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sql001.R
import com.example.sql001.viewModel.MainViewModel
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.sql001.viewModel.StudentViewData
import com.example.sql001.viewModel.SwipeToDelete
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentFragment : Fragment(R.layout.fragment_list_student) {

    private val studentsList: RecyclerView by lazy { requireView().findViewById(R.id.listStudent) }
    private val studentsAdapter: StudentsAdapter by lazy { StudentsAdapter(this::onStatusChanged) }

    val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentsList.adapter = studentsAdapter
        studentsList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.bindListStudent().observe(viewLifecycleOwner, {
            studentsAdapter.submitList(it)
        })
        val addStudent: FloatingActionButton =
            view.findViewById(R.id.addStudentFloatingActionButton)
        val editName: EditText = view.findViewById(R.id.editName)

        addStudent.setOnClickListener {
            if (editName.text.toString().isNotEmpty()) {
                viewModel.creatStudent(editName.text.toString())
            }
        }

        studentsAdapter.onItemSwiped = { studentViewData ->
            viewModel.remoovStudent(studentViewData)
        }
    }
    fun onStatusChanged(studentViewData: StudentViewData){
        viewModel.onStatusChang(studentViewData)
    }
//    val item = object : SwipeToDelete(view?.context, 0, ItemTouchHelper.LEFT) {
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        }
//    }

}