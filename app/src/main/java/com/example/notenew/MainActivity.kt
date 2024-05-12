package com.example.notenew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notenew.databinding.ActivityMainBinding
//import com.example.taskminder.databinding.ActivityMainBinding
//import com.example.taskminder.TaskDatabaseHelper
//import com.example.taskminder.TasksAdaptor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDatabaseHelper
    private lateinit var tasksAdaptor: TasksAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= TaskDatabaseHelper(this)
        tasksAdaptor= TasksAdaptor(db.getAllTasks(),this)

        binding.tasksRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter= tasksAdaptor

        binding.addbutton.setOnClickListener {
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        tasksAdaptor.refreshData(db.getAllTasks())
    }
}