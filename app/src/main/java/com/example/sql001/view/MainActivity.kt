package com.example.sql001.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.room.Room
import com.example.sql001.R
import com.example.sql001.model.db.AppDataBace
import com.example.sql001.model.DataBaceRepository
import com.example.sql001.viewModel.DBClassStudentFactory
import com.example.sql001.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        DBClassStudentFactory(
            DataBaceRepository(
                Room.databaseBuilder(this, AppDataBace::class.java, "app-db")
                    .allowMainThreadQueries()
                    .build()
                    .todoDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.bindListStudent().observe(this, {
            supportFragmentManager.commit {
                replace(R.id.container_main, ListStudentFragment())
            }

        })

        supportFragmentManager.commit {
            replace(R.id.container_main, ListStudentFragment())
        }
    }
}

