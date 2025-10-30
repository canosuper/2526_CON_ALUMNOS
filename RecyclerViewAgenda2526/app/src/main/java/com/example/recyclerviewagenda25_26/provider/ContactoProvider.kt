package com.example.recyclerviewagenda25_26.provider

import com.example.recyclerviewagenda25_26.R
import com.example.recyclerviewagenda25_26.model.Contacto

// Usamos un 'object' para implementar el patrón Singleton.
// Esto nos da una única instancia de nuestro proveedor de datos.
object ContactoProvider {

    // Hacemos la lista 'privada' para que no se pueda modificar desde fuera.
    // Solo se puede acceder a ella a través de la función pública.
    private val contactos = mutableListOf(
        Contacto(
            nombre = "Elena",
            apellidos = "García Rodríguez",
            telefono = "611223344",
            email = "elena.garcia@email.com",
            avatar = R.drawable.avatar,
            direccion = "Calle Mayor, 1",
            provincia = "Madrid"
        ),
        Contacto(
            nombre = "Carlos",
            apellidos = "Sánchez López",
            telefono = "622334455",
            email = "carlos.sanchez@email.com",
            avatar = R.drawable.avatar,
            direccion = "Avenida de la Constitución, 24",
            provincia = "Barcelona"
        ),
        Contacto(
            nombre = "Ana",
            apellidos = "Martínez Pérez",
            telefono = "633445566",
            email = "ana.martinez@email.com",
            avatar = R.drawable.avatar,
            direccion = "Plaza de España, 10",
            provincia = "Sevilla"
        ),
        Contacto(
            nombre = "David",
            apellidos = "Fernández González",
            telefono = "644556677",
            email = "david.fernandez@email.com",
            avatar = R.drawable.avatar,
            direccion = "Paseo de la Castellana, 100",
            provincia = "Madrid"
        ),
        Contacto(
            nombre = "Lucía",
            apellidos = "Ruiz Romero",
            telefono = "655667788",
            email = "lucia.ruiz@email.com",
            avatar = R.drawable.avatar,
            direccion = "Calle de la Paz, 15",
            provincia = "Valencia"
        ),
        Contacto(
            nombre = "Javier",
            apellidos = "Jiménez Saez",
            telefono = "666778899",
            email = "javier.jimenez@email.com",
            avatar = R.drawable.avatar,
            direccion = "Gran Vía, 56",
            provincia = "Bilbao"
        ),
        Contacto(
            nombre = "María",
            apellidos = "Moreno Díaz",
            telefono = "677889900",
            email = "maria.moreno@email.com",
            avatar = R.drawable.avatar,
            direccion = "Calle Larios, 5",
            provincia = "Málaga"
        ),
        Contacto(
            nombre = "Daniel",
            apellidos = "Gómez Álvarez",
            telefono = "688990011",
            email = "daniel.gomez@email.com",
            avatar = R.drawable.avatar,
            direccion = "Rambla de Méndez Núñez, 40",
            provincia = "Alicante"
        )
    )

    // Función pública para obtener toda la lista de contactos.
    // Esto encapsula los datos y sigue el patrón Repositorio.
    fun findAll(): MutableList<Contacto> {
        return contactos
    }

    // --- NUEVA FUNCIÓN ---
    // Busca un contacto por su posición en la lista.
    // Devuelve 'null' si la posición está fuera de los límites para evitar errores.
    fun findByPosition(position: Int): Contacto? {
        return if (position in contactos.indices) {
            contactos[position]
        } else {
            null
        }
    }

    // --- NUEVA FUNCIÓN ---
    // Elimina un contacto por su posición.
    fun deleteByPosition(position: Int) {
        if (position in contactos.indices) {
            contactos.removeAt(position)
        }
    }
}
