package com.jimenard.qentatest.persistence.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jimenard.qentatest.persistence.database.entities.QentaEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class QentaEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = ID_COLUMN_NAME)
    var webServiceId: String,

    @ColumnInfo(name = PREVIEW_COLUMN_NAME)
    var preview: String? = null,

    @ColumnInfo(name = IMAGE_COLUMN_NAME)
    var image: String? = null,

    @ColumnInfo(name = COMMENTS_COLUMN_NAME)
    var comments: String? = null,

    @ColumnInfo(name = USER_COLUMN_NAME)
    var user: String? = null){

    companion object {
        const val TABLE_NAME = "qenta"
        const val ID_COLUMN_NAME = "id"
        const val PREVIEW_COLUMN_NAME = "previewURL"
        const val IMAGE_COLUMN_NAME = "webformatURL"
        const val COMMENTS_COLUMN_NAME = "comments"
        const val USER_COLUMN_NAME = "user"
    }
}