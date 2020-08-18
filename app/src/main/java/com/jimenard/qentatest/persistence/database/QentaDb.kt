package com.jimenard.qentatest.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jimenard.qentatest.persistence.database.dao.QentaDao
import com.jimenard.qentatest.persistence.database.entities.QentaEntity

@Database(entities = [
    QentaEntity::class],
    version = QentaDb.VERSION,
    exportSchema = false
)
/**
 * Modelo de la base de datos
 */
    abstract class QentaDb : RoomDatabase() {

    abstract fun getQentaDb(): QentaDao

    companion object {
        private const val DATABASE_NAME = "QentaDb"
        const val VERSION = 1
        var INSTANCE: QentaDb? = null

        fun getDatabase(context: Context): QentaDb? {
            if (INSTANCE == null) {
                synchronized(QentaDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            QentaDb::class.java,
                            DATABASE_NAME
                        )
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        /**
         * Constante con hilo cuando se crea la base de datos
         * Si se necesitan triggers o sp se ponen aca
         */
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }
    }
}