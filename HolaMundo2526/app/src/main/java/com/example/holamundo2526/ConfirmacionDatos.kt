package com.example.holamundo2526

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmacionDatos : AppCompatActivity() {

    //1. Declarar las variables para los TextViews y el botón
    private lateinit var tvValorNombre: TextView
    private lateinit var tvValorEmail: TextView
    private lateinit var tvValorTelefono: TextView
    private lateinit var tvValorEdad: TextView
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout que creamos anteriormente
        setContentView(R.layout.activity_confirmacion_datos)

        // 2. Inicializar cada vista usando findViewById
        tvValorNombre = findViewById(R.id.tvValorNombre)
        tvValorEmail = findViewById(R.id.tvValorEmail)
        tvValorTelefono = findViewById(R.id.tvValorTelefono)
        tvValorEdad = findViewById(R.id.tvValorEdad)
        btnVolver = findViewById(R.id.btnVolver)

        // --- RECIBIR Y MOSTRAR LOS DATOS SIMPLES---

        // 3. Recuperar el "paquete" de datos (Bundle) del Intent
        val bundle = intent.extras

        // 4. Extraer cada dato usando la misma clave que usaste para enviar
        // Se añade "??" y un valor por defecto por si el dato viniera nulo
        val nombre = bundle?.getString("EXTRA_NOMBRE") ?: "Sin nombre"
        val email = bundle?.getString("EXTRA_EMAIL") ?: "Sin email"
        val telefono = bundle?.getString("EXTRA_TELEFONO") ?: "Sin teléfono"
        val edad = bundle?.getString("EXTRA_EDAD") ?: "Sin edad"

        // 5. Asignar los datos recuperados a los TextViews
        tvValorNombre.text = nombre
        tvValorEmail.text = email
        tvValorTelefono.text = telefono
        tvValorEdad.text = edad


        /* Descomenta este bloque si quieres recibir el objeto de la MainActivity
        // --- RECIBIR Y MOSTRAR EL OBJETO PERSONA ---

        // 3. Recuperar el objeto Parcelable del Intent
        // Se necesita un manejo diferente dependiendo de la versión de Android.
        val persona = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Forma moderna y segura para Android 13 (API 33) y superior
            intent.getParcelableExtra("EXTRA_PERSONA", Persona::class.java)
        } else {
            // Forma antigua (marcada como obsoleta) para versiones anteriores
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Persona>("EXTRA_PERSONA")
        }

        // 4. Comprobar que el objeto no es nulo y mostrar sus datos
        if (persona != null) {
            // Accedemos a las propiedades del objeto 'persona' y las asignamos
            tvValorNombre.text = persona.nombre
            tvValorEmail.text = persona.email
            tvValorTelefono.text = persona.telefono
            tvValorEdad.text = persona.edad
        } else {
            // Es una buena práctica manejar el caso en que los datos no lleguen
            tvValorNombre.text = "Error al recibir los datos"
            // Puedes dejar los otros campos en blanco o mostrar un mensaje similar
            tvValorEmail.text = "-"
            tvValorTelefono.text = "-"
            tvValorEdad.text = "-"
        }
        */

        // 6. Configurar el botón para volver a la pantalla anterior
        btnVolver.setOnClickListener {
            finish() // Cierra la Activity actual y regresa a MainActivity
        }
    }
}
