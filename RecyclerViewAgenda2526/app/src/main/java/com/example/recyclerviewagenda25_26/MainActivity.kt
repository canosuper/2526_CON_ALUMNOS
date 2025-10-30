package com.example.recyclerviewagenda25_26

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewagenda25_26.adapter.ContactoAdapter
import com.example.recyclerviewagenda25_26.databinding.ActivityMainBinding
import com.example.recyclerviewagenda25_26.provider.ContactoProvider

class MainActivity : AppCompatActivity() {
    // 2. Declarar la variable para el ViewBinding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Instalar la Splash Screen. SIEMPRE debe ser la primera llamada en onCreate.
        installSplashScreen()

        // 2. Llamar al 'super' y establecer el layout.
        super.onCreate(savedInstanceState)

        // 3. Inflar el layout usando ViewBinding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Se establece el 'root' del binding como la vista principal.

        // 4. Configurar el manejo de los bordes de la pantalla (Edge to Edge).
        //    Esta llamada es opcional, pero si se usa, debe ir después de setContentView.
        //    enableEdgeToEdge() ya no es necesaria si manejas los insets manualmente.

        // 5. Configurar el listener para los 'insets' (barras del sistema).
        //    Esto asegura que el contenido no quede oculto bajo la barra de estado o la de navegación.
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 6. Finalmente, inicializar el RecyclerView una vez que toda la UI está lista.
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // 5. Configurar el LayoutManager que usará el RecyclerView
        // LinearLayoutManager muestra los items en una lista vertical.
        binding.recyclerViewContactos.layoutManager =
            LinearLayoutManager(this)

        // 6. Crear el Adapter y pasarle la lista de contactos desde nuestro Provider
        // ContactoProvider.findAll() nos devuelve la lista completa de contactos de prueba.
        val adapter = ContactoAdapter(ContactoProvider.findAll())

        // 7. Asignar el adapter al RecyclerView
        // A partir de aquí, el RecyclerView y el Adapter se encargan de todo.
        binding.recyclerViewContactos.adapter = adapter
    }
}