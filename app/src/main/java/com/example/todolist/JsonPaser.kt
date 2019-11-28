package com.example.todolist

import org.json.JSONArray

object JsonPaser {

    /*
    var scheduleId : Int? = null
    var title: String? = ""
    var date : String? = ""
    var content: String? = ""
    var startX: Float? = null
    var startY: Float? = null
    var endX: Float? = null
    var endY: Float? = null
    */

    var parsedString : String = ""
    var jsonArray : JSONArray? = null
    fun createJsonArray(parsedString : String) : Int{
        jsonArray = JSONArray(parsedString)
        return jsonArray!!.length()
    }

    fun scheduleJsonFarse(index : Int) : Todo{

        val jObject = jsonArray!!.getJSONObject(index)

        val todo : Todo = Todo(jObject.getInt("scheduleId"), jObject.getString("title")
        ,jObject.getString("date"), jObject.getDouble("startX"), jObject.getDouble("startY"),
            jObject.getDouble("endX"), jObject.getDouble("endY"), jObject.getInt("faceMakerId"))

        return todo
    }
}