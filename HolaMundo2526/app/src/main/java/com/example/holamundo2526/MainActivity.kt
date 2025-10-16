package com.example.holamundo2526

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.semantics.dismiss
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    // Declaraciones de los EditTexts y el Botón de esta pantalla
    private lateinit var editTextNombre: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextEdad: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var radioGroupLicencia: RadioGroup
    private lateinit var radioButtonAcepto: RadioButton
    private lateinit var radioButtonNoAcepto: RadioButton

    private lateinit var botonLimpiar: Button
    private lateinit var botonMostrar: Button
    private lateinit var botonSalir: ImageButton
    private lateinit var botonEnviar: Button





    private val TAG = "ACSCO" // Un tag diferente para no confundir

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Ahora, el resto del código funcionará sin errores, porque los IDs
        // se buscarán en el archivo XML correcto.
        Log.d(TAG, "Layout 'activity_main' cargado.")
   
        // Inicialización de Vistas
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        editTextEdad = findViewById(R.id.editTextEdad)
        editTextPassword = findViewById(R.id.editTextPassword)
        radioGroupLicencia = findViewById(R.id.radioGroupLicencia)
        radioButtonAcepto = findViewById(R.id.radioButtonAcepto)
        radioButtonNoAcepto = findViewById(R.id.radioButtonNoAcepto)

        botonLimpiar = findViewById(R.id.buttonLimpiar)
        botonMostrar = findViewById(R.id.buttonMostrarDatos)
        botonSalir = findViewById(R.id.imageButtonSalir)
        botonEnviar = findViewById(R.id.buttonEnviar)

        botonEnviar.isEnabled = false
        radioButtonAcepto.setOnClickListener {
            botonEnviar.isEnabled = true
        }
        radioButtonNoAcepto.setOnClickListener {
            botonEnviar.isEnabled = false
        }

        // Configuración del OnClickListener para el botón Limpiar
        botonLimpiar.setOnClickListener {
            Log.d(TAG, "Botón Limpiar presionado.")
            limpiarFormulario()
            Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_LONG).show()
        }
        // Configuración del OnClickListener para el botón Mostrar
        botonMostrar.setOnClickListener {
            Log.d(TAG, "Botón Mostrar presionado.")
            //mostrarFormulario()
            mostrarFormularioEnAlertDialog()
            //Toast.makeText(this, "Datos del formulario mostrados", Toast.LENGTH_LONG).show()
        }
        // Configuración del OnClickListener para el botón Salir
        botonSalir.setOnClickListener {
            Log.d(TAG, "Botón Salir presionado.")
            finish()
        }
        // Dentro de tu MainActivity.kt, en el método onCreate()

        // Configuración del OnClickListener para el botón Enviar
        botonEnviar.setOnClickListener {
            enviarDatosSencillos()
            //enviarObjetos()
            Log.d(TAG, "Botón Enviar presionado.")
        }

        Log.d(TAG, "Todos los IDs han sido encontrados.")
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    /**
     * Esta función limpia el texto de todos los campos del formulario.
     */
    private fun limpiarFormulario() {
        Log.d(TAG, "Ejecutando limpiarFormulario()...")
        editTextNombre.setText("")
        editTextEmail.setText("")
        editTextTelefono.setText("")
        editTextEdad.setText("")
        editTextPassword.setText("")
        editTextNombre.requestFocus()
        Log.d(TAG, "Formulario limpiado exitosamente.")
    }
    private fun mostrarFormulario(){
        Log.d(TAG, "Ejecutando mostrarFormulario()...")
        // 1. Leer el texto actual de cada EditText
        val nombre = editTextNombre.text.toString()
        val email = editTextEmail.text.toString()
        val telefono = editTextTelefono.text.toString()
        val edad = editTextEdad.text.toString()
        val password = editTextPassword.text.toString()

        // 2. Validar si el formulario está completamente vacío
        if (nombre.isBlank() && email.isBlank() && telefono.isBlank() && edad.isBlank() && password.isBlank()) {
            val mensajeVacio = "El formulario está vacío."
            Log.d(TAG, mensajeVacio)
            Toast.makeText(this, mensajeVacio, Toast.LENGTH_SHORT).show()
            // Salimos de la función para no mostrar el Toast con los datos (que estaría vacío)
            return
        }

        // 3. Construir la cadena de texto que se mostrará en el Toast
        val datosCompletos = "Nombre: $nombre, Email: $email, Tel: $telefono, Edad: $edad, Pass: $password"

        // 4. Registrar los datos en el Logcat para depuración (buena práctica)
        Log.d(TAG, "Datos a mostrar en Toast: $datosCompletos")

        // 5. Mostrar el Toast con todos los datos
        Toast.makeText(this, datosCompletos, Toast.LENGTH_LONG).show()
    }
    private fun mostrarFormularioEnAlertDialog(){
        Log.d(TAG, "Ejecutando mostrarFormulario()...")

        // 1. Leer el texto actual de cada EditText
        val nombre = editTextNombre.text.toString()
        val email = editTextEmail.text.toString()
        val telefono = editTextTelefono.text.toString()
        val edad = editTextEdad.text.toString()
        val password = editTextPassword.text.toString()

        // 2. Validar si el formulario está completamente vacío
        if (nombre.isBlank() && email.isBlank() && telefono.isBlank() && edad.isBlank() && password.isBlank()) {
            val mensajeVacio = "El formulario está vacío."
            Log.d(TAG, mensajeVacio)
            Toast.makeText(this, mensajeVacio, Toast.LENGTH_SHORT).show()
            return
        }

        // 3. Construir el mensaje para el AlertDialog (multilínea para mayor claridad)
        val datosCompletos = """
        Nombre: $nombre
        Email: $email
        Teléfono: $telefono
        Edad: $edad
        Password: $password
    """.trimIndent()

        // 4. Registrar los datos en el Logcat para depuración
        Log.d(TAG, "Datos a mostrar en AlertDialog:\n$datosCompletos")

        // 5. Crear y mostrar el AlertDialog

        AlertDialog.Builder(this)
            .setTitle("Datos del Formulario")
            .setMessage(datosCompletos)
            .setPositiveButton("Aceptar") { dialog, _ ->
                // Esta acción se ejecuta cuando el usuario presiona "Aceptar"
                dialog.dismiss() // Cierra el diálogo
            }


            .show() // ¡Muy importante! Muestra el diálogo.
    }
    private fun enviarDatosSencillos(){
        Log.d(TAG, "Ejecutando enviarDatosSencillos()...")
        // 1. Recoger los datos de los EditText
        val nombre = editTextNombre.text.toString()
        val email = editTextEmail.text.toString()
        val telefono = editTextTelefono.text.toString()
        val edad = editTextEdad.text.toString()
        val password = editTextPassword.text.toString()

        // 2. Crear un Intent para abrir ConfirmacionDatos
        // this (contexto actual) --> ConfirmacionDatos::class.java (destino)
        val intent = Intent(this, ConfirmacionDatos::class.java)

        // 3. Añadir los datos al Intent usando "putExtra"
        // Es una buena práctica crear claves únicas para no equivocarse.
        intent.putExtra("EXTRA_NOMBRE", nombre)
        intent.putExtra("EXTRA_EMAIL", email)
        intent.putExtra("EXTRA_TELEFONO", telefono)
        intent.putExtra("EXTRA_EDAD", edad)
        // No enviamos la contraseña por seguridad, es una mala práctica mostrarla.

        // 4. Iniciar la nueva Activity con el Intent
        startActivity(intent)
    }
    private fun enviarObjetos(){
        // 1. Recoger los datos de los EditText
        val nombre = editTextNombre.text.toString()
        val email = editTextEmail.text.toString()
        val telefono = editTextTelefono.text.toString()
        val edad = editTextEdad.text.toString()
        //val password = editTextPassword.text.toString()

        val persona = Persona(nombre, email, telefono, edad)

        // 2. Crear un Intent para abrir ConfirmacionDatos
        // this (contexto actual) --> ConfirmacionDatos::class.java (destino)
        val intent = Intent(this, ConfirmacionDatos::class.java)

        intent.putExtra("EXTRA_PERSONA", persona)
        // No enviamos la contraseña por seguridad, es una mala práctica mostrarla.

        // 4. Iniciar la nueva Activity con el Intent
        startActivity(intent)
    }

}