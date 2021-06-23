package com.example.codappk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ArbolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arbol)

        val imgbtnBeUno : ImageView = findViewById(R.id.imgbtnBeUno)
        val imgbtnBeDos : ImageView = findViewById(R.id.imgbtnBeDos)
        val imgbtnBeTres : ImageView = findViewById(R.id.imgbtnBeTres)
        val imgbtnBeCuatro : ImageView = findViewById(R.id.imgbtnBeCuatro)
        val imgbtnHouse : ImageView = findViewById(R.id.imgbtnHouse)
        val imgbtnDoor : ImageView = findViewById(R.id.imgbtnDoor)
        val imgbtnOwl : ImageView = findViewById(R.id.imgbtnOwl)
        val imgbtnletrero : ImageView = findViewById(R.id.imgbtnletrero)

        imgbtnBeUno.setOnClickListener {
            val intent1 = Intent(this, LogrosActivity::class.java)
            startActivity(intent1)
        }



    }
}