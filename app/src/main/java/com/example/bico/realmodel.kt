package com.example.bico
import java.io.Serializable

data class Row(val rackTotCnt: Int, val stationName:String, val parkingBikeTotCnt:Int, val shared:Int, val stationLatitude:Float, val stationLongitude:Float){
}
data class rentBikeStatus(val row: MutableList<Row>)
data class realmodel(val rentBikeStatus:rentBikeStatus){
}
