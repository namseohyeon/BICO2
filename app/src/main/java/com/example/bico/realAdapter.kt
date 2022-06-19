package com.example.bico

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bico.databinding.ItemMainBinding
import com.naver.maps.geometry.LatLng
import java.util.*

class realViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)
class realAdapter(val context: Context, val datas:MutableList<Row>?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return datas?.size?:0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val binding = (holder as realViewHolder).binding
        val model = datas!![position]
        binding.rackTotCnt.text = " 거치대 수: "+model.rackTotCnt.toString()
        binding.stationName.text = " 위치: "+model.stationName
        binding.parkingBikeTotCnt.text = " 자전거 주차 건수: "+model.parkingBikeTotCnt.toString()
        binding.shared.text = " 거치율: "+model.shared.toString()

        //val rackTotCnt: Int, val stationName:String, val parkingBikeTotCnt:Int, val shared:Int, val stationLatitude:Float, val stationLongitude:Float

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TODO("Not yet implemented")
        return realViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun sum(datas:MutableList<Row>, position: Int):Vector<LatLng>{
        val model = datas!![position]
        var markersPosition = Vector<LatLng>()
        var Latitude = model.stationLatitude
        var Longitude = model.stationLongitude
        for (i in 0 until 1000) {
            markersPosition.add(LatLng(Latitude.toDouble(), Longitude.toDouble()))
        }
        return markersPosition;
    }

}

