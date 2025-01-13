package com.example.crudrealtime090125.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.crudrealtime090125.databinding.AgendaLayoutBinding
import com.example.crudrealtime090125.models.Agenda

class AgendaViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private val binding=AgendaLayoutBinding.bind(v)
    fun render(item: Agenda, onBorrar: (Agenda)->Unit, onEdit: (Agenda)->Unit){
        binding.tvNombre.text=item.nombre
        binding.tvEmail.text=item.email
        binding.tvCiudad.text=item.ciudad
        binding.tvSalario.text=item.sueldo.toString()
        binding.btnBorrar.setOnClickListener {
            onBorrar(item)
        }
        binding.btnEditar.setOnClickListener {
            onEdit(item)
        }
    }

}
