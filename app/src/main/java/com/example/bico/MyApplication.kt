package com.example.bico

import android.app.Application
import android.util.Log
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication:Application() {
    companion object{
//        var networkService:NetworkService
//        var networkServiceXml:NetworkService
        var networkServiceReal: NetworkService
//        val retrofit : Retrofit
//            get()=Retrofit.Builder()
//                .baseUrl("http://apis.data.go.kr/1262000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
//        val retrofitXml : Retrofit
//            get() = Retrofit.Builder()
//                .baseUrl("http://apis.data.go.kr/")
//                .addConverterFactory(TikXmlConverterFactory.create(parser))
//                .build()
        val retrofitReal : Retrofit

            get() = Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init{
//            networkService = retrofit.create(NetworkService::class.java)
//            networkServiceXml = retrofitXml.create(NetworkService::class.java)
            networkServiceReal = retrofitReal.create(NetworkService::class.java)
        }
    }
}