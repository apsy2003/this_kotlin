package com.apsy2003.harusamki

import com.apsy2003.harusamki.data.Restaurants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openAPI.seoul.go.kr:8088/"
        val API_KEY = "715852734461707339394957757347"
    }
}

interface SeoulOpenService {
    @GET("{api_key}/json/SebcKoreanRestaurantsKor/1/500")
    fun getRestaurant(@Path("api_key") key:String) : Call<Restaurants>
}