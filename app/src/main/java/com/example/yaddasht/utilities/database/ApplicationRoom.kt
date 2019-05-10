package com.example.yaddasht.utilities.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.yaddasht.model.NoteModel
import com.example.yaddasht.utilities.database.dao.NoteDao

@Database(entities = [NoteModel::class], version = 10)
abstract class ApplicationRoom : RoomDatabase() {
    abstract fun notes(): NoteDao

    companion object {
        private var roomInstance: ApplicationRoom? = null
        @Synchronized
        fun getInstance(context: Context): ApplicationRoom {
            if (roomInstance == null) {
                roomInstance =
                    Room.databaseBuilder(context.applicationContext, ApplicationRoom::class.java, "NoteDatabase")
                        .allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
            }
            return roomInstance as ApplicationRoom
        }
    }
}