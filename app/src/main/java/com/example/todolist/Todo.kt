package com.example.todolist

import java.io.Serializable

class Todo : Serializable{

    constructor(
        scheduleId: Int,
        title: String?,
        date: String?,
        startX: Double,
        startY: Double,
        endX: Double,
        endY: Double,
        facemakerId: Int
    ) {
        this.scheduleId = scheduleId
        this.title = title
        this.date = date
        this.content = content
        this.startX = startX
        this.startY = startY
        this.endX = endX
        this.endY = endY
        this.facemakerId = facemakerId
    }

    var scheduleId : Int = -1
    var title: String? = ""
    var date : String? = ""
    var content: String? = ""
    var startX: Double = 0.0
    var startY: Double = 0.0
    var endX: Double = 0.0
    var endY: Double = 0.0
    var facemakerId: Int = -1

}
