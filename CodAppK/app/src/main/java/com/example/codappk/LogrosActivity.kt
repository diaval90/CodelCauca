package com.example.codappk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import android.widget.EditText
import android.widget.Toast

data class Logros (val nombre:String, val aprendizaje:String)

class LogrosActivity : AppCompatActivity() {

    private val database = Firebase.database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)

        val myRef = database.getReference("logros")

        val txtLogroUno: EditText = findViewById(R.id.txtLogroUno)
        val txtLogroDos: EditText = findViewById(R.id.txtLogroDos)
        val BtnGuardarLogro: Button = findViewById(R.id.BtnGuardarLogro)
        val BtnEditarLogro : Button = findViewById(R.id.BtnEditarLogro)

        val logro = txtLogroUno.getText().toString()
        val logro2 = txtLogroDos.getText().toString()
        if(logro.equals("") && logro2.equals("")){
            txtLogroUno.setError("Required")
            txtLogroDos.setError("Required")
        }
    }
}