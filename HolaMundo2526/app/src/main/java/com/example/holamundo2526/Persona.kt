package com.example.holamundo2526

import android.os.Parcelable
import kotlinx.parcelize.Parcelize// 1. Añade la anotación @Parcelize
@Parcelize
// 2. Haz que la clase implemente la interfaz Parcelable
data class Persona(
    val nombre: String,
    val email: String,
    val telefono: String,
    val edad: String
    // No incluimos la contraseña por seguridad
) : Parcelable
