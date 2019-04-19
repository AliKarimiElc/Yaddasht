package com.example.yaddasht.utilities.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.yaddasht.model.CategoryModel

@Dao
abstract class CategoryDao : BaseDao<CategoryModel> {

    @Query("select * from CategoryModel")
    abstract fun get(): List<CategoryModel>

    @Query("select * from CategoryModel where catId == :id")
    abstract fun find(id: Int): List<CategoryModel>

    @Query("select * from CategoryModel where CategoryName == :name")
    abstract fun get(name: String): List<CategoryModel>
}