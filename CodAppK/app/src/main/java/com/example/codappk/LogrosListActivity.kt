package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class LogrosListActivity : AppCompatActivity() {

    private  var autentication : FirebaseAuth?=null
    private var database = FirebaseDatabase.getInstance()
    private var conexion = database.reference

    private lateinit var dbref : DatabaseReference
    private lateinit var logroRecyclerView: RecyclerView
    private lateinit var logroArrayList: ArrayList<Logro>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros_list)

        logroRecyclerView = findViewById(R.id.RecyclerLogro)
        logroRecyclerView.layoutManager = LinearLayoutManager(this)
        logroRecyclerView.setHasFixedSize(true)

        logroArrayList = arrayListOf<Logro>()
        getUserData()
    }

    private fun getUserData() {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        dbref = FirebaseDatabase.getInstance().getReference("logros").child(user)
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(logroSnapshot in snapshot.children){
                      val log = logroSnapshot.getValue(Logro::class.java)
                        Log.e("LOGRO", "${log!!.nombre}")
                        logroArrayList.add(log!!)
                    }
                    logroRecyclerView.adapter = MyAdapter(logroArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}