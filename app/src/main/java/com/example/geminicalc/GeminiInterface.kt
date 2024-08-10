package com.example.geminicalc

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GeminiInterface {
    @GET("api/hello/{question}")
    fun getResponse(@Path("question") question: String): Call<JsonObject>
}