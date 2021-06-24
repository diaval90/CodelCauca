package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*

data class Logros (val nombre:String, val aprendizaje:String, val id: String)
class LogrosActivity : AppCompatActivity() {

    private  var autentication : FirebaseAuth?=null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)

        autentication = FirebaseAuth.getInstance()

        val txtLogroUno: EditText = findViewById(R.id.txtLogroUno)
        val txtLogroDos: EditText = findViewById(R.id.txtLogroDos)
        val BtnGuardarLogro: Button = findViewById(R.id.BtnGuardarLogro)
        val BtnEditarLogro : Button = findViewById(R.id.BtnEditarLogro)

        BtnGuardarLogro.setOnClickListener {
            val logro = txtLogroUno.text.toString()
            val logro2 = txtLogroDos.text.toString()
            if (logro.equals("") && logro2.equals("")){
                txtLogroUno.setError("Required")
                txtLogroDos.setError("Required")
            }else{
                crear(logro, logro2)
            }
        }

    }

    fun crear(logro: String, logro2: String) {
        val id: String = UUID.randomUUID().toString()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        if (user != null) {
            val logros = Logros(logro, logro2, id)
            conexion.child("logros").child(user).child(logros.id).setValue(logros)
            val intent = Intent(this, LogrosActivity::class.java)
            Toast.makeText(this, "Logro agregado con exito!!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }else {
            // No user is signed in
            Toast.makeText(this, "El usuario no esta logeado", Toast.LENGTH_SHORT).show()
        }
    }
}
