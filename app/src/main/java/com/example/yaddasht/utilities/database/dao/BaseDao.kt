package com.example.yaddasht.utilities.database.dao

import android.arch.persistence.room.*

@Dao
interface BaseDao<T> {

    @Insert
    fun insert(t: T): Long

    @Insert
    fun insert(vararg t: T): List<Long>

    @Delete
    fun delete(t: T)

    @Delete
    fun delete(vararg t:T)

    @Update
    fun update(t:T)
}