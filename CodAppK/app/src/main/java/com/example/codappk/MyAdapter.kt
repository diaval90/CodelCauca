package com.example.codappk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val logros: ArrayList<Logro>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.logro_item,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = logros[position]

        holder.nombre.text = currentitem.nombre
        holder.aprendizaje.text = currentitem.aprendizaje
        holder.id.text = currentitem.id
    }

    override fun getItemCount(): Int {
        return logros.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nombre: TextView = itemView.findViewById(R.id.txtNombreLogro)
        val aprendizaje: TextView = itemView.findViewById(R.id.txtAprendizaje)
        val id: TextView = itemView.findViewById(R.id.txtId)
    }
}