package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CreateTodoActivityJava extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnClickListener {

    private GoogleMap mMap;

    private Marker startMarkrer = null;
    private Marker endMarker = null;
    private Integer selectMarker = 0;
    private Button addButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapsjava);

        //SupportMapFragment를 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addButton = findViewById(R.id.addButton);


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        //구글맵 객체 호출
        mMap = googleMap;

        //맵 터치 이벤트
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {


            @Override
            public void onMapClick(LatLng point) {

                MarkerOptions mOptions = new MarkerOptions();

                //마커의 타이틀
                mOptions.title(" 현 위치 : FaceMaker ");
                Double latitude = point.latitude; //위도
                Double longitude = point.longitude; //경도

                //마커 스니펫 설정
                //mOptions.snippet(latitude.toString() + ", " + longitude.toString());

                //LatLng : 위도,경도 표현
                mOptions.position(new LatLng(latitude,longitude));


                // 클릭 이벤트 발생 할때마다 마커 생성

                if(selectMarker == 0 ){
                    if(startMarkrer != null) {
                        startMarkrer.remove();
                    }
                    startMarkrer = googleMap.addMarker(mOptions.title("출발지점"));
                    startMarkrer.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    selectMarker += 1;
                }
                else if(selectMarker == 1){
                    if(endMarker != null){
                        endMarker.remove();
                    }
                    endMarker = googleMap.addMarker(mOptions.title("도착지점"));
                    endMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    selectMarker = 0;
                }

                Log.d("selectMarker", selectMarker.toString());

            }
        });

        //서울(여의도) 지역에 대한 위치 설정 (위도,경도)
        LatLng seoul = new LatLng(37.52487, 126.92723);

        //앱 실행시 카메라를 여의도 위치로 옮긴다.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,16f));

        //마커 클릭시 이벤트 처리
        mMap.setOnMarkerClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        //Toast.makeText(this,marker.getTitle() + "\n" + marker.getPosition(),Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}