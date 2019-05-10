package com.example.yaddasht.model

import android.arch.persistence.room.*
import java.io.Serializable

class CategoryModel : Serializable {

    @ColumnInfo(name = "CategoryName")
    var name: String? = null

    @TypeConverters(StickerColorConverter::class)
    @ColumnInfo(name = "CategoryColor")
    var color: StickerColor? = null
}

@TypeConverters(StickerColorConverter::class)
enum class StickerColor {
    Pink,
    Yellow,
    Green,
    Orange,
    Blue,
    White
}

class StickerColorConverter {

    @TypeConverter
    fun toStickerColor(color: Int): StickerColor {
        return when (color) {
            1 -> StickerColor.White
            2 -> StickerColor.Blue
            3 -> StickerColor.Green
            4 -> StickerColor.Orange
            5 -> StickerColor.Pink
            6 -> StickerColor.Yellow
            else -> throw IllegalArgumentException("Could not recognize workStatus")
        }
    }

    @TypeConverter
    fun toInteger(color: StickerColor): Int? {
        return when (color) {
            StickerColor.White -> 1
            StickerColor.Blue -> 2
            StickerColor.Green -> 3
            StickerColor.Orange -> 4
            StickerColor.Pink -> 5
            StickerColor.Yellow -> 6
        }
    }
}