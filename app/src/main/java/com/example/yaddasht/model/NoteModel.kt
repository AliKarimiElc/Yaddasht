package com.example.yaddasht.model

import android.arch.persistence.room.*
import java.io.Serializable

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true) var noteId: Int?, @ColumnInfo(name = "NoteText") var text: String?,
    @ColumnInfo(name = "DateTime") var dateTime: String?, @Embedded var category: CategoryModel?
) : Serializable