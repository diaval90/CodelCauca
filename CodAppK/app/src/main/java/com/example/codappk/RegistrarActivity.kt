package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
data class Usuarios (val user:String, val email:String, val id:String)

class RegistrarActivity : AppCompatActivity() {
    private  var autentication : FirebaseAuth?=null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        autentication = FirebaseAuth.getInstance()

        val btnRegistrate: Button = findViewById(R.id.btnRegistrate)
        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        val edtEmail : EditText = findViewById(R.id.edtEmail)
        val edtUser : EditText = findViewById(R.id.edtUser)
        val edtPassword : EditText = findViewById(R.id.edtPassword)

        btnRegistrate.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val user = edtUser.text.toString()
            if (email.equals("") && password.equals("") && user.equals("")){
                edtEmail.setError("Required")
                edtPassword.setError("Required")
                edtUser.setError("Required")
            }else{
                login(email, password, user)
            }
        }

        btnSignIn.setOnClickListener {
            val intent = Intent(this, IniciarActivity::class.java)
            startActivity(intent)
        }
    }//fin onCreate
    fun login(email: String, password:String, user: String){
        autentication!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val Uid = FirebaseAuth.getInstance().currentUser!!.uid
                    conexion.child("users").child(Uid).setValue(Usuarios(user, email, Uid))
                    val intent = Intent(this, IniciarActivity::class.java)
                    intent.putExtra("id", Uid)
                    Toast.makeText(applicationContext, "Creado con exito!!", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(applicationContext, "No creado!!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}