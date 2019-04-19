package com.example.yaddasht.utilities.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.yaddasht.model.NoteModel

@Dao
abstract class NoteDao : BaseDao<NoteModel> {

    @Query("select * from NoteModel")
    abstract fun get(): List<NoteModel>

    @Query("select * from NoteModel order by CategoryColor")
    abstract fun getOrderByColor(): List<NoteModel>

    @Query("select * from NoteModel order by DateTime desc")
    abstract fun getOrderByDateDesc(): List<NoteModel>

    @Query("select * from NoteModel order by DateTime asc")
    abstract fun getOrderByDateAsc(): List<NoteModel>

    @Query("select * from NoteModel where noteId == :id")
    abstract fun find(id: Int): NoteModel
}