package com.example.crudrealtime090125.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudrealtime090125.R
import com.example.crudrealtime090125.models.Agenda

class AgendaAdapter(
    var lista: MutableList<Agenda>,
    private val onBorrar: (Agenda)->Unit,
    private val onEdit: (Agenda)->Unit
): RecyclerView.Adapter<AgendaViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.agenda_layout, parent, false)
        return AgendaViewHolder(v)
    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        holder.render(lista[position], onBorrar, onEdit)
    }
}