package com.example.stradiotouselessapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("api/food/random_food")
    fun getRandomData(): Call<JsonObject> //Without this data class use JsonObject

    @GET("api/dessert/random_dessert")
    fun getDesertData(): Call<JsonObject>

    @GET("api/beer/random_beer")
    fun getBeerData(): Call<JsonObject>
}