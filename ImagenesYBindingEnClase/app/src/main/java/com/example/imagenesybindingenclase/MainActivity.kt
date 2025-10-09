package com.example.imagenesybindingenclase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imagenesybindingenclase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    // 1. CREAR LA LISTA DE TÁCTICAS USANDO TU CLASE
    // Esta lista contendrá todos los datos de forma organizada.
    private val listaDeTacticas = listOf(
        Tactica("4-4-2", R.drawable.t442),
        Tactica("4-3-2-1", R.drawable.t4321),
        Tactica("4-3-3", R.drawable.t433), // Corregí el nombre que tenías como "4-3-3-3"
        Tactica("4-5-1", R.drawable.t451),
        Tactica("5-4-1", R.drawable.t541)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // setContentView(R.layout.activity_main)
        // 3. Infla el layout y asigna la instancia de binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 4. Establece la vista raíz del binding como el contenido de la actividad
        setContentView(binding.root)

        binding.mini1.setOnClickListener {
            binding.imgPrincipal.setImageResource(listaDeTacticas[0].imagen)
            binding.tvTactica.text = listaDeTacticas[0].nombre

        }
        binding.mini2.setOnClickListener {
            binding.imgPrincipal.setImageResource(listaDeTacticas[1].imagen)
            binding.tvTactica.text = listaDeTacticas[1].nombre
        }
        binding.mini3.setOnClickListener {
            binding.imgPrincipal.setImageResource(listaDeTacticas[2].imagen)
            binding.tvTactica.text = listaDeTacticas[2].nombre
        }
        binding.mini4.setOnClickListener {
            binding.imgPrincipal.setImageResource(listaDeTacticas[3].imagen)
            binding.tvTactica.text = listaDeTacticas[3].nombre
        }
        binding.mini5.setOnClickListener {
            binding.imgPrincipal.setImageResource(listaDeTacticas[4].imagen)
            binding.tvTactica.text = listaDeTacticas[4].nombre
        }



    }
}