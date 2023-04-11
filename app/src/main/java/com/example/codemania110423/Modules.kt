package com.example.codemania110423

import android.content.Context
import android.icu.text.CaseMap.Title
import android.webkit.ConsoleMessage
import androidx.appcompat.app.AlertDialog
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("http://95.31.130.149:8085/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val api = retrofit.create(API::class.java)

var token = ""

data class RegB(
    val firstname:String,
    val lastname:String,
    val patronymic:String,
    val sex:String,
    val dateBirthDay:String,
    val email:String,
    val password:String
)
data class LogB(
    val email:String,
    val password: String
)
data class userData(
    val id:Int,
    val name:String,
    val lastname:String,
    val patronymic:String,
    val avatar:String?,
    val sex:String,
    val dateofbirth:String,
    val email:String,
    val password:String
)
data class responstoken(
    val user:userData,
    val token:String,
    val error:String?
)
data class errorModel(
    val error:String
        )
fun error(context: Context,title: String,message: String){
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton("OK",null)
        .show()


}