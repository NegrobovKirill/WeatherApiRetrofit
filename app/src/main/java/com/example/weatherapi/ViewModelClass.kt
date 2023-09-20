package com.example.weatherapi

import androidx.lifecycle.ViewModel
import com.example.weatherapi.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelClass: ViewModel() {

    val retrofit = Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/").addConverterFactory(
        GsonConverterFactory.create()).build()

    val mainApi = retrofit.create(MainApi::class.java)


    private var city = "London"
    private var temp = ""
    private var date = ""

    fun setCity(text: String){
        city = text
    }

    fun getCurrentTemp(): String{
        return temp
    }

    fun getCurrentDate(): String{
        return date
    }

    fun getCurrentCity(): String{
        return city
    }

    suspend fun runRequest(){

            val modelApi = mainApi.getWeatherData(
                "a66b4a2ded3b40e5af0225921230909 ",
                city,
                "1",
                "no",
                "no"
            )

            temp = modelApi.current.temp_c.toString()
            date = modelApi.location.localtime


    }



}