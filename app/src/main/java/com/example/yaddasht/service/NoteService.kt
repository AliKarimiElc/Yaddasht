package com.example.yaddasht.service

import android.content.Context
import com.example.yaddasht.model.NoteModel
import com.example.yaddasht.utilities.database.ApplicationRoom

class NoteService {
    companion object {

        var noteSorting: NoteSort = NoteSort.DateDec

        fun getNoteList(context: Context, sorting: NoteSort): List<NoteModel> {
            noteSorting = sorting
            val room = ApplicationRoom.getInstance(context)
            when (sorting) {
                NoteSort.DateDec -> return room.notes().getOrderByDateDesc()
                NoteSort.DateAsc -> return room.notes().getOrderByDateAsc()
                else -> sorting == NoteSort.Color
            }
            return room.notes().getOrderByColor()
        }

        fun addNote(context:Context , note:NoteModel){
            val room=ApplicationRoom.getInstance(context)
            room.notes().insert(note)
        }

        fun deleteNote(context:Context , note:NoteModel){
            val room=ApplicationRoom.getInstance(context)
            room.notes().delete(note)
        }

        fun updateNote(context:Context , note:NoteModel){
            val room=ApplicationRoom.getInstance(context)
            room.notes().update(note)
        }

    }
}

enum class NoteSort {
    DateAsc,
    DateDec,
    Color
}