package com.jimenard.qentatest.persistence.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.jimenard.qentatest.persistence.database.entities.QentaEntity

@Dao
abstract class QentaDao : GenericDao<QentaEntity> {

    @Query("select * from ${QentaEntity.TABLE_NAME} limit 20")
    abstract suspend fun getImagesFromDatabase(): List<QentaEntity>

    @Query("select ${QentaEntity.IMAGE_COLUMN_NAME} from ${QentaEntity.TABLE_NAME} where ${QentaEntity.ID_COLUMN_NAME}  like :imageId")
    abstract suspend fun getCurrentImage(imageId:String): String
}