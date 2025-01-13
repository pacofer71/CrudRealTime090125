package com.example.crudrealtime090125.providers

import com.example.crudrealtime090125.models.Agenda
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AgendaProvider {
    private val database=FirebaseDatabase.getInstance().getReference("agenda")
    fun getDatos(datosAgenda: (MutableList<Agenda>)->Unit){
        database.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val listado= mutableListOf<Agenda>() //mutable list vacia
                for(item in snapshot.children){
                    val valor=item.getValue(Agenda::class.java)
                    if(valor!=null){
                        listado.add(valor)
                    }
                }
                listado.sortBy { it.nombre }
                datosAgenda(listado)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al leer realtime: ${error.message}")
            }

        })
    }
}