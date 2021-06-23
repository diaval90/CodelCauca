package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class IniciarActivity : AppCompatActivity() {
    private lateinit var autentication: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar)
        autentication = FirebaseAuth.getInstance()

        val loginBtn: Button = findViewById(R.id.loginBtn)
        val login_mail: EditText = findViewById(R.id.login_mail)
        val login_password: EditText = findViewById(R.id.login_password)

        loginBtn.setOnClickListener {
            val email = login_mail.text.toString()
            val pass = login_password.text.toString()
            login(email, pass)
        }
    }
    private fun login(email:String, pass:String){
        autentication.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            if (it.isSuccessful){
                val idUser = FirebaseAuth.getInstance().currentUser!!.uid
                val intent = Intent(this, ArbolActivity::class.java)
                intent.putExtra("id", idUser)
                startActivity(intent)
            }
            else{
                runOnUiThread {
                    Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}