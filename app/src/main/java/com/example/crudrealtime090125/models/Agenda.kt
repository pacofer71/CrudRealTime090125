package com.example.crudrealtime090125.models

import java.io.Serializable

// Es obligatorio para este caso inicializar todos los valores
data class Agenda(
    val email: String="",
    val nombre: String="",
    val ciudad: String="",
    val sueldo: Float=0F
): Serializable
