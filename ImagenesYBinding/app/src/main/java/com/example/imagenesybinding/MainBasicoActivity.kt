package com.example.imagenesybinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imagenesybinding.databinding.ActivityMainBasicoBinding

class MainBasicoActivity : AppCompatActivity() {
    // Se usa 'lateinit' porque la inicializaremos en onCreate.
    private lateinit var binding: ActivityMainBasicoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // Paso 1: Configurar el modo "borde a borde" ANTES que nada.
        enableEdgeToEdge()

        // Paso 2: Llamada al método de la clase padre.
        super.onCreate(savedInstanceState)

        // Paso 3: Inflar la vista usando View Binding. Esto crea los objetos de las vistas.
        binding = ActivityMainBasicoBinding.inflate(layoutInflater)

        // Paso 4: Establecer el contenido de la actividad usando la raíz del binding.
        setContentView(binding.root)

        // El bloque ViewCompat se ha eliminado porque es redundante.
        // enableEdgeToEdge() ya aplica los 'insets' (márgenes) a la vista
        // principal de tu layout (la que tiene android:fitsSystemWindows="true",
        // que en tu caso es el ConstraintLayout con id "main").

        // --- A partir de aquí, añade la lógica de tu app ---

        // Ejemplo: Configurar los listeners para las miniaturas.
        binding.miniatura1.setOnClickListener {
            binding.imageViewGrande.setImageResource(R.drawable.t442)
            binding.textViewNombreImagen.text = "4-4-2"
            // Aquí iría la lógica para actualizar los bordes de selección
        }
        binding.miniatura2.setOnClickListener {
            binding.imageViewGrande.setImageResource(R.drawable.t433)
            binding.textViewNombreImagen.text = "4-3-3"
            // Aquí iría la lógica para actualizar los bordes de selección
        }
        binding.miniatura3.setOnClickListener{
            binding.imageViewGrande.setImageResource(R.drawable.t4321)
            binding.textViewNombreImagen.text = "4-3-2-1"
            // Aquí iría la lógica para actualizar los bordes de selección
        }
        binding.miniatura4.setOnClickListener{
            binding.imageViewGrande.setImageResource(R.drawable.t451)
            binding.textViewNombreImagen.text = "4-5-1"
            // Aquí iría la lógica para actualizar los bordes de selección
        }
        binding.miniatura5.setOnClickListener {
            binding.imageViewGrande.setImageResource(R.drawable.t541)
            binding.textViewNombreImagen.text = "5-4-1"
            // Aquí iría la lógica para actualizar los bordes de selección
        }


    }
}