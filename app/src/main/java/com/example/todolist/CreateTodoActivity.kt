package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_mapsjava.*
import org.jetbrains.anko.startActivity

class CreateTodoActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private var mMap: GoogleMap? = null

    private var startMarkrer: Marker? = null
    private var endMarker: Marker? = null
    private var selectMarker: Int? = 0
    //private var addButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapsjava)

        //SupportMapFragment를 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)


        addButton.setOnClickListener {
            var date: String = """${dateText.text} ${timeText.text}"""
            val todo: Todo = Todo(
                -1,
                todoNameText.text.toString(),
                date,
                startMarkrer?.position!!.latitude,
                startMarkrer?.position!!.longitude,
                endMarker?.position!!.latitude,
                endMarker?.position!!.longitude,
                1
            )

            HttpManager.insertRequset(this, todo) { testSuccess ->
                if (testSuccess) {
                    // Toast.makeText(this, HttpManager.result, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "통신 실패...!", Toast.LENGTH_LONG).show()
                }
            }

            startActivity<MainActivity>()

        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        //구글맵 객체 호출
        mMap = googleMap

        //맵 터치 이벤트
        mMap!!.setOnMapClickListener { point ->
            val mOptions = MarkerOptions()

            //마커의 타이틀
            //mOptions.title(" 현 위치 : FaceMaker ")
            val latitude = point.latitude //위도
            val longitude = point.longitude //경도

            //마커 스니펫 설정
            //mOptions.snippet(latitude.toString() + ", " + longitude.toString());

            //LatLng : 위도,경도 표현
            mOptions.position(LatLng(latitude, longitude))


            // 클릭 이벤트 발생 할때마다 마커 생성

            if (selectMarker == 0) {
                if (startMarkrer != null) {
                    startMarkrer!!.remove()
                }
                startMarkrer = googleMap.addMarker(mOptions.title("출발지점"))
                startMarkrer!!.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                selectMarker = 1
            } else if (selectMarker == 1) {
                if (endMarker != null) {
                    endMarker!!.remove()
                }
                endMarker = googleMap.addMarker(mOptions.title("도착지점"))
                endMarker!!.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                selectMarker = 0
            }

            Log.d("selectMarker", selectMarker!!.toString())
        }

        //서울(여의도) 지역에 대한 위치 설정 (위도,경도)
        val seoul = LatLng(37.52487, 126.92723)

        //앱 실행시 카메라를 여의도 위치로 옮긴다.
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 16f))

        //마커 클릭시 이벤트 처리
        mMap!!.setOnMarkerClickListener(this)
    }


    override fun onMarkerClick(marker: Marker): Boolean {
        //Toast.makeText(this,marker.getTitle() + "\n" + marker.getPosition(),Toast.LENGTH_SHORT).show();
        return false
    }
}