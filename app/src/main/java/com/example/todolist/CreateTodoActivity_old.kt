package com.example.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_todo.*
import kotlinx.android.synthetic.main.content_create_todo.*

class CreateTodoActivity_old : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val todo :Todo = Todo(-1, todoText.text.toString(),
                    timeText.text.toString(),
                    startXtext.text.toString().toDouble(),
                    startYtext.text.toString().toDouble(),
                    endXtext.text.toString().toDouble(),
                    endYtext.text.toString().toDouble(),
                1)

            HttpManager.insertRequset(this, todo){ testSuccess ->
                if (testSuccess) {
                    // Toast.makeText(this, HttpManager.result, Toast.LENGTH_LONG).show()
                    } else {
                    Toast.makeText(this, "통신 실패...!", Toast.LENGTH_LONG).show()
                }
            }


            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
