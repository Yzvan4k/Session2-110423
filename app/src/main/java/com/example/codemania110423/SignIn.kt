package com.example.codemania110423

import android.content.Intent
import android.media.session.MediaSession.Token
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Response

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val editMail = findViewById<EditText>(R.id.editMail)
        val editPass = findViewById<EditText>(R.id.editPassword)

        button2.setOnClickListener { startActivity(Intent(this,SignUp::class.java)) }

        button.setOnClickListener {
            val InMail = editMail.text.toString()
            if (InMail.isBlank()){
                error(this,"error","Empty Mail")
            }else{
                if (!Patterns.EMAIL_ADDRESS.matcher(InMail).matches()){
                    error(this,"error","Wrong Mail")
                }
            }
            val InPass = editPass.text.toString()
            if (InPass.isBlank()){
                error(this,"error","Empty Password")
            }
            api.login(LogB(InMail,InPass)).enqueue(object :retrofit2.Callback<responstoken>{
                override fun onResponse(call: Call<responstoken>,
                    response: Response<responstoken>) {
                    if (response.isSuccessful && response.body() != null){
                        token = response.body()!!.token
                        startActivity(Intent(this@SignIn,Main::class.java))
                        error(this@SignIn,"token",response.body()!!.token) }
                    else error(this@SignIn,"Error","Unknown Error")
                }

                override fun onFailure(call: Call<responstoken>, t: Throwable) {
                    error(this@SignIn,"Error",t.localizedMessage?:t.message?:"Unknown Error")
                }
            })
        }

    }
}