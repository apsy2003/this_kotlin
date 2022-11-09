package com.apsy2003.harusamki

import com.apsy2003.harusamki.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openAPI.seoul.go.kr:8088/"
        val API_KEY = "59714c4e7061707338346874666779"
    }
}

interface SeoulOpenService {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/203")
    fun getLibrary(@Path("api_key") key:String) : Call<Library>
}