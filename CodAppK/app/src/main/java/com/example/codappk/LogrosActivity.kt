package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class LogrosActivity : AppCompatActivity() {

    private  var autentication : FirebaseAuth?=null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)

        autentication = FirebaseAuth.getInstance()

        val txtLogroUno: EditText = findViewById(R.id.EditLogro)
        val txtLogroDos: EditText = findViewById(R.id.EditAprendizaje)
        val BtnGuardarLogro: Button = findViewById(R.id.BtnEditSave)
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
        BtnEditarLogro.setOnClickListener {
            val intent = Intent(this, LogrosListActivity::class.java)
            startActivity(intent)
        }

    }

    fun crear(logro: String, logro2: String) {
        val id: String = UUID.randomUUID().toString()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        if (user != null) {
            val logros = Logro(logro, logro2, id)
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
