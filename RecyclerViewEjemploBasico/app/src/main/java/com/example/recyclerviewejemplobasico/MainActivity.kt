package com.example.recyclerviewejemplobasico

import adapter.AdaptadorCantantes
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdaptadorCantantes
    private lateinit var cantantes: ArrayList<String>
    private lateinit var arrowUp: ImageView
    private lateinit var arrowDown: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos la lista de cantantes
        cantantes = arrayListOf(
            "Billie Eilish",
            "Dua Lipa",
            "Harry Styles",
            "Olivia Rodrigo",
            "The Weeknd",
            "Doja Cat",
            "Bad Bunny",
            "Taylor Swift",
            "Rosalía",
            "Ariana Grande"
        )
        arrowUp = findViewById(R.id.imUp)
        arrowDown = findViewById(R.id.imDown)
        // Configuramos el RecyclerView
        recyclerView = findViewById(R.id.rvCantantes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdaptadorCantantes(cantantes)
        // Crear un DividerItemDecoration y agregarlo al RecyclerView
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        // Agregar un ScrollListener al RecyclerView para mostrar/ocultar las flechas
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                // Mostrar la flecha hacia abajo si no está en el final
                if ((firstVisibleItemPosition + visibleItemCount) < totalItemCount) {
                    // Mostrar flecha abajo
                    arrowDown.visibility = View.VISIBLE
                } else {
                    // Ocultar flecha abajo
                    arrowDown.visibility = View.GONE
                }

                // Mostrar la flecha hacia arriba si no está en el inicio
                if (firstVisibleItemPosition > 0) {
                    // Mostrar flecha arriba
                    arrowUp.visibility = View.VISIBLE
                } else {
                    // Ocultar flecha arriba
                    arrowUp.visibility = View.GONE
                }
            }
        })

    }
}
