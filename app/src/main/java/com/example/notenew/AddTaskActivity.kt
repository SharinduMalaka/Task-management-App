package com.example.notenew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.notenew.databinding.ActivityAddTaskBinding
//import com.example.taskminder.databinding.ActivityAddTaskBinding



class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var databaseHelper: TaskDatabaseHelper // Use lateinit for non-null variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize databaseHelper with the correct context
        databaseHelper = TaskDatabaseHelper(this)

        binding.savebutton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim() // Trim leading/trailing whitespace

            // Validate title before inserting
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(this, "Please enter a title for the task", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit the click listener if title is empty
            }

            val task = Task(0, title) // Create a Task object
            databaseHelper.insertTask(task) // Call insertNote with the correct method name

            finish() // Close the activity after successful insertion
            Toast.makeText(this, "Task Saved Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}
