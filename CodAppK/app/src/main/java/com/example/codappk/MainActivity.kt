package com.example.codappk

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClick(view: View) {
        var intent1: Intent? = null
        when (view.id) {
            R.id.btnRegistro -> intent1 = Intent(this@MainActivity, RegistrarActivity::class.java)
            R.id.btnInicio -> intent1 = Intent(this@MainActivity, IniciarActivity::class.java)
        }
        startActivity(intent1)
    }
}