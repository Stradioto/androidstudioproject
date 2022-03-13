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

class APIVIEWMODEL: ViewModel() {

    val fetchedData = mutableStateOf("")
    val fetchedData2 = mutableStateOf("")

    val api by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<API>()
    }

    fun getRandomData() {
        viewModelScope.launch {
            val resp = api.getRandomData().awaitResponse()
                if (resp.isSuccessful) {
                    val data: JsonObject? = resp.body()
                    fetchedData.value = data!!.get("dish").asString
                    fetchedData2.value = data!!.get("description").asString
                    Log.d("*", data!!.get("dish").asString)
                }
        }
    }
}