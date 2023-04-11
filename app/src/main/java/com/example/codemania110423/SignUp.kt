package com.example.codemania110423

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val Upname = findViewById<EditText>(R.id.editName)
        val UpSecondname = findViewById<EditText>(R.id.editLastName)
        val Uppatronymic = findViewById<EditText>(R.id.editPatronymic)
        val Upsex = findViewById<TextView>(R.id.textView15)
        val Updateofbirth = findViewById<EditText>(R.id.editDateOfBirth)
        val Upmail = findViewById<EditText>(R.id.editMail)
        val Uppass = findViewById<EditText>(R.id.editPass)
        val Uppass2 = findViewById<EditText>(R.id.editPass2)

        button3.setOnClickListener { startActivity(Intent(this,SignIn::class.java)) }

        Upsex.setOnClickListener {
            val menu = PopupMenu(this,Upsex)
            menu.inflate(R.menu.menu)
            menu.setOnMenuItemClickListener {
                Upsex.text = it.title
                return@setOnMenuItemClickListener true
            }
            menu.show()
        }



        button4.setOnClickListener {
            val upname = Upname.text.toString()
            if (upname.isBlank()){
                error(this, "Error", "Empty name")
                return@setOnClickListener
            }
            val uplastname = UpSecondname.text.toString()
            if (uplastname.isBlank()){
                error(this, "Error", "Empty Lastname")
                return@setOnClickListener
            }
            val uppatronymic = Uppatronymic.text.toString()
            if (uppatronymic.isBlank()){
                error(this, "Error", "Empty patronymic")
                return@setOnClickListener
            }
            val upsex = Upsex.text.toString()


            val updateofbirth = Updateofbirth.text.toString()
            if (updateofbirth.isBlank()){
                error(this, "Error", "Empty Date")
                return@setOnClickListener
            }
            val upmail = Upmail.text.toString()
            if (upmail.isBlank()){
                error(this, "Error", "Empty mail")
                return@setOnClickListener
            }
            val uppass = Uppass.text.toString()
            if (uppass.isBlank()){
                error(this, "Error", "Empty password")
                return@setOnClickListener
            }
            val uppass2 = Uppass2.text.toString()
            if (uppass2.isBlank()){
                error(this, "Error", "Empty password")
                return@setOnClickListener
            }
            api.reg(RegB(upname,uplastname,uppatronymic,upsex,updateofbirth,upmail,uppass)).enqueue(object :retrofit2.Callback<responstoken>{
                override fun onResponse(call: Call<responstoken>, response: Response<responstoken>) {
                    if (response.body() != null && response.body()?.error == null){
                        startActivity(Intent(this@SignUp,Main::class.java))
                    }else{
                        if (response.body() == null){
                            if (response.errorBody()==null){
                                error(this@SignUp, "Error", response.body().toString())
                            } else{
                                error(this@SignUp, "Error", Gson().fromJson(response.errorBody()!!.string(), errorModel::class.java).error
                                )
                            }
                        }
                        else if (response.body()!!.error != null){
                            error(this@SignUp, "Error", response.body()!!.error!!)
                        }
                    }
                }


                override fun onFailure(call: Call<responstoken>, t: Throwable) {
                    error(this@SignUp, "Error", t.localizedMessage ?: t.message ?: "Unknown Error")
                }
            })
        }
    }
}