package com.example.recyclerviewagenda25_26.model

data class Contacto(
    val nombre: String,
    val apellidos: String,
    val telefono: String,        val email: String,
    val avatar: Int, // Referencia a un recurso drawable (ej. R.drawable.avatar1)
    val direccion: String,
    val provincia: String
)
