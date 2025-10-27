package adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewejemplobasico.R


class AdaptadorCantantes(private val cantantes: ArrayList<String>) : RecyclerView.Adapter<CantanteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CantanteViewHolder {
        // Inflamos el layout de cada elemento
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cantante, parent, false)
        return CantanteViewHolder(view)
    }
    override fun onBindViewHolder(holder: CantanteViewHolder, position: Int) {
        // Inicializamos la lista de cantantes


        val cantante = cantantes[position]
        holder.textView.text = cantante

        // Al hacer clic, el fondo del ítem cambia de color
        holder.itemView.setOnClickListener {
            if (!holder.isBackgroundColorChanged) {
                holder.itemView.setBackgroundColor(Color.LTGRAY)
                holder.isBackgroundColorChanged = true
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT) // Or your default color
                holder.isBackgroundColorChanged = false
            }
        }

        // Al dejar pulsado, se elimina el ítem de la lista
        holder.itemView.setOnLongClickListener {
            cantantes.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cantantes.size)
            true
        }
    }
    override fun getItemCount(): Int {
        return cantantes.size
    }

}


