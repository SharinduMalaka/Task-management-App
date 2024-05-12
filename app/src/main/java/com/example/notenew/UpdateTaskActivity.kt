package com.example.notenew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notenew.databinding.ActivityUpdateBinding
//import com.example.taskminder.databinding.ActivityUpdateBinding

class UpdateTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db:TaskDatabaseHelper
    private   var taskId: Int= -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= TaskDatabaseHelper(this)
        taskId= intent.getIntExtra("task_id",-1)
        if(taskId== -1){
            finish()
            return
        }

        val task= db.getTaskById(taskId)
        binding.updatetitleEditText.setText(task.title)

        binding.updatesavebutton.setOnClickListener {
            val newTitle = binding.updatetitleEditText.text.toString().trim()  // Use getText().toString().trim()
            val updatedTask = Task(taskId, newTitle);
            db.updateTask(updatedTask);
            finish();
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
        }

    }
}

