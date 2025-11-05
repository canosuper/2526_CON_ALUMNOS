package com.example.misseries_room_sqllite_2526.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.misseries_room_sqllite_2526.bd.Serie
import com.example.misseries_room_sqllite_2526.databinding.ItemSerieBinding

class SerieAdapter(private var series: List<Serie>) : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {

    /**
     * ViewHolder: Representa una única fila (un 'item') en el RecyclerView.
     * Contiene las referencias a las vistas dentro del layout 'item_serie.xml'.
     * Usamos View Binding aquí también para acceder a las vistas de forma segura.
     */
    inner class SerieViewHolder(val binding: ItemSerieBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Se llama cuando el RecyclerView necesita crear un nuevo ViewHolder (una nueva fila).
     * Infla el layout 'item_serie.xml' y crea una instancia de SerieViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        // Inflamos el layout para cada item usando View Binding
        val binding = ItemSerieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SerieViewHolder(binding)
    }

    /**
     * Se llama para mostrar los datos en una posición específica.
     * Vincula los datos del objeto 'Serie' en la posición 'position' con las vistas del ViewHolder.
     */
    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie = series[position] // Obtenemos la serie actual de la lista

        // Usamos el 'holder.binding' para acceder a los TextViews y asignarles los datos
        holder.binding.tvTitulo.text = serie.titulo
        holder.binding.tvPlataforma.text = serie.plataforma
        holder.binding.tvGenero.text = serie.genero
        // Formateamos el texto para las temporadas
        holder.binding.tvTemporadas.text = "${serie.temporadas} Temp."
    }

    /**
     * Devuelve el número total de items en la lista de datos.
     */
    override fun getItemCount(): Int {
        return series.size
    }

    /**
     * Función personalizada para actualizar la lista de series en el adaptador.
     * Después de actualizar los datos, notifica al RecyclerView para que se redibuje.
     */
    fun actualizarLista(nuevaLista: List<Serie>) {
        series = nuevaLista
        notifyDataSetChanged() // Este método le dice al adapter que los datos han cambiado
    }
}
