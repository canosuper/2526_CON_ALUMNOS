package com.example.logincongoogle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity2 : AppCompatActivity() {
    // 1. Cliente para el inicio de sesión con Google (método clásico)
    private lateinit var googleSignInClient: GoogleSignInClient

    // 2. Cliente de autenticación de Firebase
    private lateinit var firebaseAuth: FirebaseAuth

    // 3. ActivityResultLauncher para gestionar el resultado del inicio de sesión
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView( R.layout.activity_main2)

        // 4. Configurar las opciones de Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // Este token se obtiene del archivo google-services.json que añadiste.
            // Es crucial para la comunicación con Firebase.
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail() // Pedimos el email del usuario
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // 5. Inicializar Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // 6. Registrar el callback que gestionará la respuesta de la ventana de Google
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Comprobamos si el usuario seleccionó una cuenta y aceptó (RESULT_OK)
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Si la tarea tiene éxito, obtenemos la cuenta
                    val account = task.getResult(ApiException::class.java)!!
                    // Usamos el token de la cuenta para autenticarnos en Firebase
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Si algo falla (ej. el usuario cancela, error de red), mostramos un error
                    showToast("Error en el login con Google: ${e.message}")
                }
            }
        }

        // 7. Configurar el OnClickListener del botón de login
        val signInButton = findViewById<SignInButton>(R.id.google_sign_in_button)
        signInButton.setOnClickListener {
            signInWithGoogle()
        }


//        val signOutButton = findViewById<Button>(R.id.sign_out_button)
//        signOutButton.setOnClickListener {
//            signOut() // Llama a nuestra nueva función
//        }

    }

//    override fun onStart() {
//        super.onStart()
//        // Comprobar si el usuario ya ha iniciado sesión al abrir la app
//        val currentUser = firebaseAuth.currentUser
//        updateUI(currentUser)
//    }

    // 8. Función para lanzar la ventana de selección de cuenta de Google
    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    // 9. Función para autenticar en Firebase usando la credencial de Google
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // ¡Éxito! El usuario está autenticado en Firebase.
                    val user = firebaseAuth.currentUser
                    //updateUI(user)
                    showToast("Login exitoso: ${user?.displayName}")

                    // En el futuro, desde aquí llamarías a la siguiente pantalla de tu app.
                } else {
                    // La autenticación con Firebase falló por alguna razón.
                   // updateUI(null)
                    showToast("Falló la autenticación con Firebase.")
                }
            }
    }

    // Función de ayuda para mostrar mensajes Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

//    private fun signOut() {
//        // 1. Cerrar sesión en Firebase
//        firebaseAuth.signOut()
//
//        // 2. Cerrar sesión en Google
//        googleSignInClient.signOut().addOnCompleteListener(this) {
//            // Tarea completada. Podemos actualizar la interfaz.
//            updateUI(null) // Pasamos null porque ya no hay usuario
//            showToast("Sesión cerrada correctamente.")
//        }
//    }
    private fun updateUI(firebaseUser: FirebaseUser?) {
        val signInButton = findViewById<SignInButton>(R.id.google_sign_in_button)
        val signOutButton = findViewById<Button>(R.id.sign_out_button)
        // 1. Buscamos el nuevo TextView por su ID
        val userInfoTextView = findViewById<TextView>(R.id.user_info_textview)

        if (firebaseUser != null) {
            // Usuario está logueado:
            // Ocultar botón de login
            signInButton.visibility = View.GONE

            // Mostrar el TextView y el botón de logout
            userInfoTextView.visibility = View.VISIBLE
            signOutButton.visibility = View.VISIBLE

            // 2. Personalizamos el texto con el nombre del usuario
            userInfoTextView.text = "Bienvenido, ${firebaseUser.displayName}"

        } else {
            // Usuario no está logueado:
            // Mostrar botón de login
            signInButton.visibility = View.VISIBLE

            // Ocultar el TextView y el botón de logout
            userInfoTextView.visibility = View.GONE
            signOutButton.visibility = View.GONE
        }
    }




}