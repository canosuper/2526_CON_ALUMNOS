package com.example.misseries_room_sqllite_2526.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Esta clase define la estructura de la tabla 'series_table' en la base de datos.
 * Cada propiedad en la clase corresponde a una columna en la tabla.
 */
@Entity(tableName = "series_table")
data class Serie(
    // Clave primaria que se autogenera. El 'id' será único para cada serie.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Columna para el título de la serie.
    @ColumnInfo(name = "titulo")
    val titulo: String,

    // Columna para el número de temporadas.
    @ColumnInfo(name = "temporadas")
    val temporadas: Int,

    // Columna para el género.
    @ColumnInfo(name = "genero")
    val genero: String,

    // Columna para la plataforma.
    @ColumnInfo(name = "plataforma")
    val plataforma: String
)