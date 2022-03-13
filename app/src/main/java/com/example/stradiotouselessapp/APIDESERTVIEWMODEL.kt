package com.example.stradiotouselessapp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class APIDESERTVIEWMODEL: ViewModel() {

    val desertData = mutableStateOf("")
    val desertData2 = mutableStateOf("")

    val api by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<API>()
    }

    fun getDesertData() {
        viewModelScope.launch {
            val resp = api.getDesertData().awaitResponse()
                if (resp.isSuccessful) {
                    val data: JsonObject? = resp.body()
                    desertData.value = data!!.get("variety").asString
                    desertData2.value = data!!.get("topping").asString
                }
        }
    }
}