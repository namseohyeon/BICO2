package com.example.bico

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("{api_key}/json/bikeList/1/1000/")
    fun getRealList(

        @Path("api_key")apikey:String?
    ): Call<realmodel>
}