package com.example.todolist

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object HttpManager {

    val requestScheduleUrl = "http://10.10.16.81:8081/schedule"
    var result : String = ""

    fun getRequset(context: Context, success: (Boolean) -> Unit) {

        val myJson = JSONObject()
        val requestBody = myJson.toString()
        /* myJson에 아무 데이터도 put 하지 않았기 때문에 requestBody는 "{}" 이다 */

        val getRequest =
            object : StringRequest(Method.GET, requestScheduleUrl, Response.Listener { response ->
                Log.d("result?","서버 Response 수신: ${response}")

                //한글이 깨지므로 이렇게 처리해준다.
                val utf8String = String(response.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)
                result = utf8String

                success(true)
            }, Response.ErrorListener { error ->
                Log.d("ERROR?", "서버 Response 가져오기 실패: $error")
                success(false)
            })

            {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }
                /* getBodyContextType에서는 요청에 포함할 데이터 형식을 지정한다.
             * getBody에서는 요청에 JSON이나 String이 아닌 ByteArray가 필요하므로, 타입을 변경한다. */
            }

        Volley.newRequestQueue(context).add(getRequest)
    }

    fun deleteByIdRequest(context: Context, id: Int?, success: (Boolean) -> Unit) {

        val deleteUrl : String = "$requestScheduleUrl/$id"

        //Log.d(dele)

        val myJson = JSONObject()
        val requestBody = myJson.toString()
        /* myJson에 아무 데이터도 put 하지 않았기 때문에 requestBody는 "{}" 이다 */

        val deleteRequest =
            object : StringRequest(Method.DELETE, deleteUrl, Response.Listener { response ->
                Log.d("result?","서버 Response 수신: ${response}")

                //한글이 깨지므로 이렇게 처리해준다.
                val utf8String = String(response.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)
                result = utf8String

                success(true)
            }, Response.ErrorListener { error ->
                Log.d("ERROR?", "서버 Response 가져오기 실패: $error")
                success(false)
            })

            {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }
                /* getBodyContextType에서는 요청에 포함할 데이터 형식을 지정한다.
             * getBody에서는 요청에 JSON이나 String이 아닌 ByteArray가 필요하므로, 타입을 변경한다. */
            }

        Volley.newRequestQueue(context).add(deleteRequest)
    }

    fun insertRequset(context: Context, todo : Todo ,success: (Boolean) -> Unit){
        Log.d("start", "here")
        val insertUrl = "$requestScheduleUrl"
        var myJson = JSONObject()

        val params = HashMap<String,String>()
        params["scheduleId"] = todo.scheduleId.toString()
        params["title"]  = todo.title.toString()
        params["date"] = todo.date.toString()
        params["startX"] = todo.startX.toString()
        params["startY"] = todo.startY.toString()
        params["endX"] = todo.endX.toString()
        params["endY"] = todo.endY.toString()
        params["faceMakerId"] = todo.facemakerId.toString()

        myJson = JSONObject(params.toMap())

        Log.d("myJSon", myJson.toString())

        val requestBody = myJson.toString()
        val postRequest =
            object : StringRequest(Method.POST, insertUrl, Response.Listener { response ->
                Log.d("result?","서버 Response 수신: $response")

                //한글이 깨지므로 이렇게 처리해준다.
                val utf8String = String(response.toByteArray(Charsets.ISO_8859_1), Charsets.UTF_8)
                result = utf8String

                success(true)
            }, Response.ErrorListener { error ->
                Log.d("ERROR?", "서버 Response 가져오기 실패: $error")
                success(false)
            })

            {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }
                /* getBodyContextType에서는 요청에 포함할 데이터 형식을 지정한다.
             * getBody에서는 요청에 JSON이나 String이 아닌 ByteArray가 필요하므로, 타입을 변경한다. */

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String,String>()
                    params["scheduleId"] = todo.scheduleId.toString()
                    params["title"]  = todo.title.toString()
                    params["date"] = todo.date.toString()
                    params["startX"] = todo.startX.toString()
                    params["startY"] = todo.startY.toString()
                    params["endX"] = todo.endX.toString()
                    params["endY"] = todo.endY.toString()
                    params["faceMakerId"] = todo.facemakerId.toString()

                    myJson = JSONObject(params.toMap())

                    Log.d("myJSon", myJson.toString())

                    return params
                }
            }

        Volley.newRequestQueue(context).add(postRequest)
    }
}