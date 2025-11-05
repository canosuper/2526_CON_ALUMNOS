package com.example.misseries_room_sqllite_2526.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** * Clase principal de la base de datos.
 * Es abstracta y hereda de RoomDatabase.
 *
 * @Database define la lista de entidades (tablas) y la versión de la BD.
 */
@Database(entities = [Serie::class], version = 1, exportSchema = false)
abstract class SeriesDatabase : RoomDatabase() {

    // Método abstracto que devolverá una instancia de nuestro DAO.
    // Room se encargará de generar la implementación.
    abstract fun serieDao(): SerieDao

    // 'companion object' permite acceder a los métodos sin necesidad de crear una instancia de la clase.
    // Aquí implementamos el patrón Singleton.
    companion object {
        // La anotación @Volatile asegura que el valor de INSTANCE esté siempre actualizado
        // y sea visible para todos los hilos de ejecución.
        @Volatile
        private var INSTANCE: SeriesDatabase? = null

        /**
         * Función para obtener la instancia única de la base de datos.
         * Si la instancia ya existe, la devuelve. Si no, la crea.
         * El bloque 'synchronized' asegura que solo un hilo a la vez pueda crear la instancia,
         * evitando así la creación de múltiples bases de datos por error.
         */
        fun getDatabase(context: Context): SeriesDatabase {
            // Si INSTANCE no es nula, la devolvemos. Si es nula, entramos al bloque synchronized.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SeriesDatabase::class.java,
                    "series_database" // Este será el nombre del fichero de la base de datos en el dispositivo.
                ).build()
                INSTANCE = instance
                // Devolvemos la instancia recién creada.
                instance
            }
        }
    }
}
