package com.example.codappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.math.log


class LogrosListActivity : AppCompatActivity() {

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
                    var adapter = MyAdapter(logroArrayList)
                    logroRecyclerView.adapter = adapter
                    adapter.setonItemClicklistener(object: MyAdapter.onItemClicklistener{
                        override fun onItemClick(position: Int) {
                            Toast.makeText(applicationContext, "${position}", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LogrosListActivity, EditarLogroActivity::class.java)
                            var lis = logroArrayList[position]
                            intent.putExtra("Logro", lis.nombre)
                            intent.putExtra("Aprend", lis.aprendizaje)
                            intent.putExtra("id", lis.id)
                            startActivity(intent)
                        }
                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}