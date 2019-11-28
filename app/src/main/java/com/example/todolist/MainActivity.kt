package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val adapter = TodoListAdapter()

        HttpManager.getRequset(this) { testSuccess ->
            if (testSuccess) {
                // Toast.makeText(this, HttpManager.result, Toast.LENGTH_LONG).show()
                val jsonArraySize : Int = JsonPaser.createJsonArray(HttpManager.result)
                for(index in 0 until jsonArraySize-1){
                    Log.d("arraySize", jsonArraySize.toString())
                    val todo = JsonPaser.scheduleJsonFarse(index)
                    adapter.addTodo(todo)
                }

            } else {
                Toast.makeText(this, "통신 실패...!", Toast.LENGTH_LONG).show()
            }

        listView.adapter = adapter




       // adapter.notifyDataSetChanged()


        }

        fab.setOnClickListener {



            startActivity<CreateTodoActivity>()
            /*    view ->

        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()*/
        }
    }
}
