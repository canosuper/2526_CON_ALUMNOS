package com.example.misseries_room_sqllite_2526

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misseries_room_sqllite_2526.bd.Serie
import com.example.misseries_room_sqllite_2526.bd.SeriesDatabase
import com.example.misseries_room_sqllite_2526.databinding.ActivityMainBinding // ¡Importante! Importar la clase de binding
import kotlinx.coroutines.launch
// <-- CAMBIO1: Importar el nuevo adaptador
import com.example.misseries_room_sqllite_2526.adapter.SerieAdapter

class MainActivity : AppCompatActivity() {

    // --- 1. DECLARACIÓN DE VARIABLES ---

    // Variable para el View Binding. Proporcionará acceso a todas las vistas.
    private lateinit var binding: ActivityMainBinding
    // <-- CAMBIO 2: Crear la variable para el adaptador
    private lateinit var adapter: SerieAdapter

    // Variable para el DAO, que nos da acceso a las operaciones de la BD
    private val serieDao by lazy {
        SeriesDatabase.getDatabase(this).serieDao()
    }

    // El adaptador para el RecyclerView (lo crearemos en el siguiente paso)
    // private lateinit var adapter: SerieAdapter // Descomentaremos esto más tarde


    // --- 2. MÉTODO ONCREATE ---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Establecemos la vista raíz del binding como el contenido de la actividad
        setContentView(binding.root)

        // ¡Ya no necesitamos 'inicializarVistas()'!

        // Configuramos los listeners de los botones
        configurarListeners()

        // Configuramos el RecyclerView
        configurarRecyclerView()

        // Cargamos todas las series al iniciar la app
        cargarTodasLasSeries()
    }


    // --- 3. FUNCIONES AUXILIARES ---

    private fun configurarListeners() {
        // Accedemos a los botones directamente a través del objeto 'binding'
        binding.btnGuardar.setOnClickListener {
            guardarSerie()
        }
        binding.btnBuscar.setOnClickListener {
            buscarSerie()
        }
        binding.btnEliminar.setOnClickListener {
            eliminarSerie()
        }
    }

    private fun configurarRecyclerView() {

        // Inicializamos el adaptador con una lista vacía al principio
        adapter = SerieAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // Asignamos el adaptador al RecyclerView
        binding.recyclerView.adapter = adapter
    }

    // --- 4. OPERACIONES CON LA BASE DE DATOS ---

    private fun guardarSerie() {
        // Recogemos los datos usando 'binding'
        val titulo = binding.etTitulo.text.toString()
        val temporadasStr = binding.etTemporadas.text.toString()
        val genero = binding.etGenero.text.toString()
        val plataforma = binding.etPlataforma.text.toString()

        // Validamos que los campos no estén vacíos
        if (titulo.isBlank() || temporadasStr.isBlank() || genero.isBlank() || plataforma.isBlank()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val temporadas = temporadasStr.toInt()
        val serie = Serie(titulo = titulo, temporadas = temporadas, genero = genero, plataforma = plataforma)

        // Lanzamos una corrutina para la operación de BD
        lifecycleScope.launch {
            val serieExistente = serieDao.buscarSeriePorTitulo(titulo)
            if (serieExistente != null) {
                // Si existe, actualizamos sus datos manteniendo su ID original
                val serieActualizada = serie.copy(id = serieExistente.id)
                serieDao.actualizarSerie(serieActualizada)
                Toast.makeText(this@MainActivity, "Serie '${titulo}' actualizada", Toast.LENGTH_SHORT).show()
            } else {
                // Si no existe, la insertamos como nueva
                serieDao.insertarSerie(serie)
                Toast.makeText(this@MainActivity, "Serie '${titulo}' guardada", Toast.LENGTH_SHORT).show()
            }
            // Después de guardar, limpiamos los campos y recargamos la lista
            limpiarCampos()
            cargarTodasLasSeries()
        }
    }

    private fun buscarSerie() {
        val titulo = binding.etTitulo.text.toString()
        if (titulo.isBlank()) {
            Toast.makeText(this, "Introduce un título para buscar", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val serie = serieDao.buscarSeriePorTitulo(titulo)
            if (serie != null) {
                // Rellenamos los campos con los datos de la serie encontrada
                binding.etTitulo.setText(serie.titulo)
                binding.etTemporadas.setText(serie.temporadas.toString())
                binding.etGenero.setText(serie.genero)
                binding.etPlataforma.setText(serie.plataforma)
                Toast.makeText(this@MainActivity, "Serie encontrada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "No se encontró la serie '${titulo}'", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun eliminarSerie() {
        val titulo = binding.etTitulo.text.toString()
        if (titulo.isBlank()) {
            Toast.makeText(this, "Busca una serie antes de eliminarla", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val serie = serieDao.buscarSeriePorTitulo(titulo)
            if (serie != null) {
                serieDao.eliminarSerie(serie)
                Toast.makeText(this@MainActivity, "Serie '${titulo}' eliminada", Toast.LENGTH_SHORT).show()
                limpiarCampos()
                cargarTodasLasSeries()
            } else {
                Toast.makeText(this@MainActivity, "No se puede eliminar una serie que no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cargarTodasLasSeries() {
        lifecycleScope.launch {
            val listaSeries = serieDao.obtenerTodasLasSeries()
            // Aquí es donde actualizaremos el adaptador.
            // <-- CAMBIO 3: Actualizar el adaptador con la lista de la BD
            adapter.actualizarLista(listaSeries)
           // Toast.makeText(this@MainActivity, "${listaSeries.size} series en la lista", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        binding.etTitulo.text?.clear()
        binding.etTemporadas.text?.clear()
        binding.etGenero.text?.clear()
        binding.etPlataforma.text?.clear()
        binding.etTitulo.requestFocus() // Ponemos el foco de nuevo en el título
    }
}
