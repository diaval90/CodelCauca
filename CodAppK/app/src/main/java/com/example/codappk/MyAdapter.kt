package com.example.codappk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val RecyclerLogro: ArrayList<Logro>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.logro_item,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = RecyclerLogro[position]

        holder.NombreLogro.text = currentitem.nombre
        holder.Aprendizaje.text = currentitem.aprendizaje
        holder.id.text = currentitem.id
    }

    override fun getItemCount(): Int {
        return RecyclerLogro.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val NombreLogro: TextView = itemView.findViewById(R.id.txtNombreLogro)
        val Aprendizaje: TextView = itemView.findViewById(R.id.txtAprendizaje)
        val id: TextView = itemView.findViewById(R.id.txtId)
    }
}