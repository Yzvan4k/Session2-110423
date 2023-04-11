package com.example.codemania110423

import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("signIn")
    fun login(@Body b:LogB):retrofit2.Call<responstoken>
    @POST("signUp")
    fun reg(@Body b:RegB):retrofit2.Call<responstoken>
}