package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_todo.view.*
import org.jetbrains.anko.startActivity

class TodoListAdapter() : BaseAdapter() {

    var TodoList = mutableListOf<Todo>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) : View? {

        val vh : com.example.todolist.ViewHolder
        val view: View

        if(convertView == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_todo, parent, false)

            vh = com.example.todolist.ViewHolder(view)
            view.tag = vh
        }
        else{
            view = convertView
            vh = view.tag as com.example.todolist.ViewHolder
        }

        val item = TodoList[position]
        vh.textTextView.text = item.title
        vh.dateTextView.text = item.date


        Log.d("Title", item.title)
        Log.d("date", item.date)


        view.buttonDelete.setOnClickListener {
            HttpManager.deleteByIdRequest(
                view.context,
                TodoList.get(position).scheduleId
            ) { testSuccess ->
                if (testSuccess) {
                    Toast.makeText(view.context, "삭제 성공!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(view.context, "삭제 실패!", Toast.LENGTH_LONG).show()
                }
                TodoList.removeAt(position)
                notifyDataSetChanged()
            }
        }

        view.setOnClickListener(){
           Log.d("position", "$position 번쩨 아이템")
            if (parent != null) {

                //view Adapter에서 화면을 바꾸려면 부모의 메서드를 호출해야함.
                parent.context.startActivity<MapsActivity>(
                    "todo" to TodoList[position]
                )
            }
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return position.toLong()
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return TodoList.size
    }

    fun addTodo(content : Todo){
        TodoList.add(content)
    }



}

class ViewHolder(view: View){
    val dateTextView: TextView = view.findViewById(R.id.text1)
    val textTextView: TextView = view.findViewById(R.id.text2)
}