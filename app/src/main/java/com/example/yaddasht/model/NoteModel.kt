package com.example.yaddasht.model

import android.arch.persistence.room.*
import java.io.Serializable

@Entity
class NoteModel : Serializable {

    @PrimaryKey(autoGenerate = true)
    var noteId: Int? = null

    @ColumnInfo(name = "NoteText")
    var text: String? = null

    @ColumnInfo(name = "DateTime")
    var dateTime: String? = null

    @Embedded
    var category: CategoryModel? = null
}