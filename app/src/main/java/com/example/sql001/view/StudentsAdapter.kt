package com.example.sql001.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sql001.R
import com.example.sql001.viewModel.StudentViewData

class StudentsAdapter(private val clickChkBox:( studentViewData : StudentViewData)->Unit) :
    ListAdapter<StudentViewData, StudentViewHolder>(StudentDifutil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student, parent, false)
        return StudentViewHolder(view)
    }

    var onItemSwiped: (studentViewData: StudentViewData) -> Unit = { }

    override fun onBindViewHolder(holderStudent: StudentViewHolder, position: Int) {
        val thisStudentData = getItem(position)
        holderStudent.bind(thisStudentData, clickChkBox)

        holderStudent.itemView.findViewById<TextView>(R.id.student_name).setOnClickListener {
            onItemSwiped.invoke(thisStudentData)
        }
    }
}

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val studentId: TextView = view.findViewById(R.id.student_id)
    private val studentName: TextView = view.findViewById(R.id.student_name)
    private val checkBox: CheckBox = view.findViewById(R.id.radioButton)

    fun bind(studentViewData: StudentViewData, clickChkBox: (item: StudentViewData) -> Unit) {
        studentId.text = studentViewData.id.toString()
        studentName.text = studentViewData.name
        checkBox.isChecked = studentViewData.isStudent
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            if (b != studentViewData.isStudent) {
                clickChkBox.invoke(studentViewData.copy(isStudent = b))
            }
        }

    }
}

class StudentDifutil : DiffUtil.ItemCallback<StudentViewData>() {
    override fun areItemsTheSame(oldItem: StudentViewData, newItem: StudentViewData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: StudentViewData, newItem: StudentViewData): Boolean {
        return oldItem == newItem
    }

}
