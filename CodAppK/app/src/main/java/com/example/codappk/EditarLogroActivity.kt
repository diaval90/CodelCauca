package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class EditarLogroActivity : AppCompatActivity() {

    private  var autentication : FirebaseAuth?=null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_logro)

        var nombrelogro: EditText = findViewById(R.id.EditLogro)
        var aprend: EditText = findViewById(R.id.EditAprendizaje)
        var buton: Button = findViewById(R.id.BtnEditSave)
        var b: Bundle = getIntent().getExtras()!!

        nombrelogro.setText(b.get("Logro").toString())
        aprend.setText(b.get("Aprend").toString())


        buton.setOnClickListener {
            saveEdit(b.get("Aprend").toString(),b.get("id").toString() ,b.get("Logro").toString())
        }
    }

    fun saveEdit(logro2: String, id:String, logro: String){
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        if (user != null) {
            val logros = Logro(logro2, id, logro)
            conexion.child("logros").child(id).setValue(logros)
            val intent = Intent(this, LogrosListActivity::class.java)
            Toast.makeText(this, "Logro editado con exito!!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }else {
            // No user is signed in
            Toast.makeText(this, "El usuario no esta logeado", Toast.LENGTH_SHORT).show()
        }
    }
}