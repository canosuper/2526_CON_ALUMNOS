package com.example.recyclerviewagenda25_26

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewagenda25_26.databinding.ActivityDetalleBinding
import com.example.recyclerviewagenda25_26.provider.ContactoProvider

class DetalleActivity : AppCompatActivity() {

    // Variable para acceder a las vistas del layout de forma segura con ViewBinding
    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout y establecerlo como la vista de la actividad
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Recuperar la posición del contacto que nos llega desde MainActivity a través del Intent.
        // Se pasa una clave ("CONTACTO_POSITION") y un valor por defecto (-1) por si ocurre un error.
        val position = intent.getIntExtra("CONTACTO_POSITION", -1)

        // 2. Comprobar que la posición es válida (no es -1).
        if (position != -1) {
            // 3. Usar la posición para pedirle el objeto Contacto completo a nuestra fuente de datos.
            val contacto = ContactoProvider.findByPosition(position)

            // 4. Si el ContactoProvider nos devuelve un contacto (no es nulo), rellenamos la UI.
            if (contacto != null) {
                binding.ivDetalleAvatar.setImageResource(contacto.avatar)
                binding.tvDetalleNombre.text = "${contacto.nombre} ${contacto.apellidos}"
                binding.tvDetalleTelefono.text = contacto.telefono
                binding.tvDetalleEmail.text = contacto.email
                binding.tvDetalleDireccion.text = contacto.direccion
                binding.tvDetalleProvincia.text = contacto.provincia
            }
        }
    }
}
