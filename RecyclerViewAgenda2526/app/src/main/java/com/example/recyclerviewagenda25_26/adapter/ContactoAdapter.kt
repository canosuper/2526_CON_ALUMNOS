package com.example.recyclerviewagenda25_26.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewagenda25_26.DetalleActivity
import com.example.recyclerviewagenda25_26.R
import com.example.recyclerviewagenda25_26.model.Contacto
import com.example.recyclerviewagenda25_26.provider.ContactoProvider // <-- IMPORTANTE

class ContactoAdapter(private val listaContactos: MutableList<Contacto>) :
    RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>() {

    class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declarar las vistas dentro del ViewHolder
        val avatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val telefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val email: TextView = itemView.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        // Inflar la vista del item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    override fun getItemCount(): Int = listaContactos.size

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        // Asignar los datos del contacto a la vista
        val contactoActual = listaContactos[position]
        holder.nombre.text = "${contactoActual.nombre} ${contactoActual.apellidos}"
        // ... (resto de asignaciones sin cambios)
        holder.avatar.setImageResource(contactoActual.avatar)
        holder.telefono.text = contactoActual.telefono
        holder.email.text = contactoActual.email


        // --- LÓGICA DE CLIC NORMAL ---
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleActivity::class.java)
            // ¡Ahora pasamos la posición, no el objeto!
            intent.putExtra("CONTACTO_POSITION", position)
            context.startActivity(intent)
        }

        // --- LÓGICA DE PULSACIÓN LARGA ---
        holder.itemView.setOnLongClickListener {
            val context = holder.itemView.context
            AlertDialog.Builder(context)
                .setTitle("Eliminar Contacto")
                .setMessage("¿Estás seguro de que quieres eliminar a ${contactoActual.nombre}?")
                .setPositiveButton("Sí") { _, _ ->
                    // 1. Pedimos al Provider que elimine el dato de la fuente original.
                    ContactoProvider.deleteByPosition(position)

                    // 2. Notificamos al RecyclerView para que actualice la UI.
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listaContactos.size)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }
}
