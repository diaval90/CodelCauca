package com.example.codappk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


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
        dbref = FirebaseDatabase.getInstance().getReference("Logros")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(logroSnapshot in snapshot.children){
                        val logro = logroSnapshot.getValue(Logro::class.java)
                            logroArrayList.add(logro!!)
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