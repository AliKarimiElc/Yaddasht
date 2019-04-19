package com.example.yaddasht.model.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yaddasht.NoteActivity
import com.example.yaddasht.R
import com.example.yaddasht.model.NoteModel
import com.example.yaddasht.model.StickerColor
import com.example.yaddasht.service.NoteService
import com.example.yaddasht.utilities.database.ApplicationRoom
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class NoteListAdapter(private var list: List<NoteModel>, private val context: Context) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItem(list[position])
        holder.deleteBtn.setOnClickListener {
            val room = ApplicationRoom.getInstance(context)
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("حذف یادداشت")
                .setMessage("آیا می خواهید این یادداشت را حذف کنید؟").setCancelable(false)
                .setPositiveButton("بله") { dialog, id ->
                    NoteService.deleteNote(context, list[position])
                    list = NoteService.getNoteList(context,NoteService.noteSorting)
                    notifyDataSetChanged()
                }.setNegativeButton("خیر") { dialog, id ->

                }
            val dialog = alertDialogBuilder.create()
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view, context)
    }

    class NoteViewHolder(override val containerView: View, private val context: Context) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(note: NoteModel) {
            textNote.text = note.text
            timeView.text = note.dateTime
            if (note.category != null && note.category?.color != null) {
                when (note.category?.color) {
                    StickerColor.Orange -> {
                        textNote.background = context.getDrawable(R.drawable.card_view_orange)
                        listCard.background = context.getDrawable(R.drawable.card_view_orange)
                        deleteBtn.background= context.getDrawable(R.drawable.card_view_orange)
                    }
                    StickerColor.White -> {
                        textNote.background = context.getDrawable(R.drawable.card_view_background)
                        listCard.background = context.getDrawable(R.drawable.card_view_background)
                        deleteBtn.background = context.getDrawable(R.drawable.card_view_background)
                    }
                    StickerColor.Blue -> {
                        textNote.background = context.getDrawable(R.drawable.card_view_blue)
                        listCard.background = context.getDrawable(R.drawable.card_view_blue)
                        deleteBtn.background = context.getDrawable(R.drawable.card_view_blue)
                    }
                    StickerColor.Pink -> {
                        textNote.background = context.getDrawable(R.drawable.card_view_pink)
                        listCard.background = context.getDrawable(R.drawable.card_view_pink)
                        deleteBtn.background = context.getDrawable(R.drawable.card_view_pink)
                    }
                    StickerColor.Yellow -> {
                        textNote.background = context.getDrawable(R.drawable.card_view_yellow)
                        listCard.background = context.getDrawable(R.drawable.card_view_yellow)
                        deleteBtn.background = context.getDrawable(R.drawable.card_view_yellow)
                    }
                    StickerColor.Green -> {
                        textNote.background = context.getDrawable(R.drawable.card_view_green)
                        listCard.background = context.getDrawable(R.drawable.card_view_green)
                        deleteBtn.background = context.getDrawable(R.drawable.card_view_green)
                    }

                }
            }
            textNote.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra("note", note)
                context.startActivity(intent)
            }
        }

        var deleteBtn = deleteButton!!
    }
}


