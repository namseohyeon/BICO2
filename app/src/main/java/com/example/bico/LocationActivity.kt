package com.example.bico

import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*


class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var marker:Marker
    private lateinit var markersPosition: Vector<LatLng>
    val activeMarkers: ArrayList<Marker>? = null
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)

        var thread = NetworkThread()
        thread.start()

        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)


    }


    override fun onMapReady(@NonNull naverMap: NaverMap) {
        this.naverMap = naverMap

        val uiSettings: UiSettings = naverMap.getUiSettings()
        uiSettings.setCompassEnabled(true); // 기본값 : true
        uiSettings.setScaleBarEnabled(true); // 기본값 : true
        uiSettings.setZoomControlEnabled(true); // 기본값 : true
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.setScrollGesturesEnabled(true)

        //배경 지도 선택
        naverMap.mapType = NaverMap.MapType.Basic

        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        //건물 표시
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)


//        //위치 및 각도 조정
//        val cameraPosition = CameraPosition(
//            LatLng(naverMap.cameraPosition.target.latitude,
//                naverMap.cameraPosition.target.longitude),
//            // 위치 지정
//            15.0,  // 줌 레벨
//        )
//        naverMap.cameraPosition = cameraPosition
//        val path = PathOverlay()
//        path.coords = listOf(
//            LatLng(37.57152, 126.97714),
//            LatLng(37.56607, 126.98268),
//            LatLng(37.56445, 126.97707),
//            LatLng(37.55855, 126.97822)
//        )
//        path.map = naverMap

    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
        inner class NetworkThread: Thread(){
        override fun run() {
            try {
                // 접속할 페이지 주소: Site
                var site =
                    "http://openapi.seoul.go.kr:8088/444977436373796e353379584c666e/json/bikeList/450/1400/"
                var url = URL(site)
                Log.d("mobileApp", "plz1")
                var conn = url.openConnection()
                Log.d("mobileApp", "plz2")
                var input = conn.getInputStream()
                Log.d("mobileApp", "plz3")
                var isr = InputStreamReader(input)
                Log.d("mobileApp", "plz4")
                // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
                var br = BufferedReader(isr)

                // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
                var str: String? = null
                var buf = StringBuffer()

                do {
                    str = br.readLine()

                    if (str != null) {
                        buf.append(str)
                    }
                } while (str != null)

                // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴

                var root = JSONObject(buf.toString());
                Log.d("mobileApp", "${root}")

                val obj1 = root.getJSONObject("rentBikeStatus")
                Log.d("mobileApp", "${obj1}")

                runOnUiThread {
                    val row = obj1.getJSONArray("row")
                    Log.d("mobileApp", "${row}")
                    for (i in 0..950) {
                        var obj = row.getJSONObject(i)
                        Log.d("mobileApp", "${obj}")
                        var stationLongitude: Double = obj.getDouble("stationLongitude")
                        var stationLatitude: Double = obj.getDouble("stationLatitude")
                        Log.d("mobileApp", "${stationLongitude}")
//                        markersPosition.add(LatLng(stationLongitude, stationLatitude))
                        val marker = Marker()
                        marker.position = LatLng(stationLatitude,stationLongitude)
                        Log.d("mobileApp", "${stationLongitude}, ${stationLatitude}")
                        activeMarkers?.add(marker)
                        Log.d("mobileApp", "${activeMarkers}")
                        marker.setMap(naverMap)
                        //Log.d("mobileApp", "${activeMarkers}")
                       //marker.map = naverMap
//                        marker.position = markersPosition[i]
                        marker.map = naverMap
                    }

                }
//                runOnUiThread {
//                  for (i in 1..100) {
//                       var marker = activeMarkers?.get(i)!!
//                       marker.setMap(naverMap);
//                      marker.map = naverMap
//                    }
//
//                }
            }catch (e: JSONException)
            {
                println(e)
            }
        }
        }
}
