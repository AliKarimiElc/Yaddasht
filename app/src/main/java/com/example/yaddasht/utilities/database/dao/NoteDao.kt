package com.example.yaddasht.utilities.database.dao

import android.arch.persistence.room.*
import com.example.yaddasht.model.NoteModel

@Dao
interface NoteDao{

    @Insert
    fun insert(note:NoteModel): Long

    @Insert
    fun insert(vararg note:NoteModel): List<Long>

    @Delete
    fun delete(note:NoteModel)

    @Delete
    fun delete(vararg note:NoteModel)

    @Update
    fun update(note:NoteModel)

    @Query("select * from NoteModel")
    fun get(): List<NoteModel>

    @Query("select * from NoteModel order by CategoryColor")
    fun getOrderByColor(): List<NoteModel>

    @Query("select * from NoteModel order by DateTime desc")
    fun getOrderByDateDesc(): List<NoteModel>

    @Query("select * from NoteModel order by DateTime asc")
    fun getOrderByDateAsc(): List<NoteModel>

    @Query("select * from NoteModel where noteId == :id")
    fun find(id: Int): NoteModel
}