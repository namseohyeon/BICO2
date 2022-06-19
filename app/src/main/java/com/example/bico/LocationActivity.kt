package com.example.bico

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import java.util.*


class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var markersPosition: Vector<LatLng>
    private var activeMarkers: Vector<Marker>? = null
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private val LOCATION_PERMISSTION_REQUEST_CODE: Int = 1000
    private lateinit var locationSource: FusedLocationSource // 위치를 반환하는 구현체

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSTION_REQUEST_CODE)

    }


    override fun onMapReady(@NonNull naverMap: NaverMap) {
//        this.naverMap = naverMap
//        naverMap.locationSource = locationSource
//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
//        naverMap.setMapType(NaverMap.MapType.Basic);
//
//        naverMap.maxZoom=18.0
//        naverMap.minZoom=10.0
//        this.naverMap = naverMap
//        naverMap.locationSource = locationSource
//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
//        val cameraPosition = CameraPosition(
//            LatLng(33.38, 126.55),  // 위치 지정
//            15.0 // 줌 레벨
//        )
//        naverMap.cameraPosition = cameraPosition
        this.naverMap = naverMap

        //배경 지도 선택
        naverMap.mapType = NaverMap.MapType.Basic

        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        //건물 표시
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE, true)

        //위치 및 각도 조정

        //위치 및 각도 조정
        val cameraPosition = CameraPosition(
            LatLng(naverMap.cameraPosition.target.latitude,
                naverMap.cameraPosition.target.longitude),  // 위치 지정
            15.0,  // 줌 레벨
        )
        naverMap.cameraPosition = cameraPosition

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
//    fun getCurrentPosition(naverMap: NaverMap): LatLng {
//        val cameraPosition = naverMap.cameraPosition
//        return LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude)
//    }
//
//    val REFERANCE_LAT = 1 / 109.958489129649955
//    val REFERANCE_LNG = 1 / 88.74
//    val REFERANCE_LAT_X3 = 3 / 109.958489129649955
//    val REFERANCE_LNG_X3 = 3 / 88.74
//    fun withinSightMarker(currentPosition: LatLng, markerPosition: LatLng): Boolean {
//        val withinSightMarkerLat =
//            Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT_X3
//        val withinSightMarkerLng =
//            Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG_X3
//        return withinSightMarkerLat && withinSightMarkerLng
//    }
//
//    // 지도상에 표시되고있는 마커들 지도에서 삭제
//    private fun freeActiveMarkers() {
//        if (activeMarkers == null) {
//            activeMarkers = Vector()
//            return
//        }
//        for (activeMarker in activeMarkers!!) {
//            activeMarker.map = null
//        }
//        activeMarkers = Vector()
//    }
}