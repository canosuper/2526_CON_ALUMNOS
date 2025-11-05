package com.example.misseries_room_sqllite_2526.bd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * DAO (Data Access Object) para la entidad Serie.
 * Define las operaciones permitidas sobre la tabla de series.
 */
@Dao
interface   SerieDao {

    /**
     * Inserta una nueva serie en la base de datos.
     * Si la serie ya existe (misma clave primaria), la operación falla.
     * Usamos 'suspend' para que se ejecute en una corrutina (en segundo plano).
     */
    @Insert
    suspend fun insertarSerie(serie: Serie)

    /**
     * Busca y devuelve una serie específica a través de su título.
     * La consulta SQL se define en la anotación @Query.
     * Devuelve un objeto 'Serie' o 'null' si no se encuentra.
     */
    @Query("SELECT * FROM series_table WHERE titulo = :titulo LIMIT 1")
    suspend fun buscarSeriePorTitulo(titulo: String): Serie?

    /**
     * Actualiza los datos de una serie existente.
     * Room busca la serie por su clave primaria (el 'id') y aplica los cambios.
     */
    @Update
    suspend fun actualizarSerie(serie: Serie)

    /**
     * Elimina una serie de la base de datos.
     * Room identifica la serie a borrar por su clave primaria.
     */
    @Delete
    suspend fun eliminarSerie(serie: Serie)

    /**
     * Devuelve una lista con todas las series de la tabla, ordenadas por título.
     * Útil para mostrar todos los datos o para pruebas.
     */
    @Query("SELECT * FROM series_table ORDER BY titulo ASC")
    suspend fun obtenerTodasLasSeries(): List<Serie>
}
