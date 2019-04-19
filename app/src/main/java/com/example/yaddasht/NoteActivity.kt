package com.example.yaddasht

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.yaddasht.model.CategoryModel
import com.example.yaddasht.model.NoteModel
import com.example.yaddasht.model.StickerColor
import com.example.yaddasht.service.NoteService
import com.example.yaddasht.utilities.CustomToast
import com.example.yaddasht.utilities.DateTimeHelper
import com.example.yaddasht.utilities.database.ApplicationRoom
import kotlinx.android.synthetic.main.activity_note.*


class NoteActivity : AppCompatActivity() {

    private lateinit var note: NoteModel
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_save) {
            if (note.noteId == null) {

                val colors = arrayOf("سفید", "صورتی", "زرد", "سبز", "آبی", "نارنجی")
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("انتخاب رنگ")
                    .setCancelable(true)
                alertDialogBuilder.setItems(colors, DialogInterface.OnClickListener { dialog, which ->
                    note.category = CategoryModel()
                    when (which) {
                        0 -> note.category?.color = StickerColor.White
                        1 -> note.category?.color = StickerColor.Pink
                        2 -> note.category?.color = StickerColor.Yellow
                        3 -> note.category?.color = StickerColor.Green
                        4 -> note.category?.color = StickerColor.Blue
                        5 -> note.category?.color = StickerColor.Orange
                    }
                    note.text = noteText.text.toString()
                    note.dateTime = DateTimeHelper.getCurrentDateTimeStringJalali()
                    NoteService.addNote(this,note)
                    CustomToast.toast(this, Toast.LENGTH_SHORT, Gravity.CENTER, "ذخیره انجام شد")
                    this.finish()
                })
                val dialog = alertDialogBuilder.create()
                dialog.show()

            } else {
                note.text = noteText.text.toString()
                NoteService.updateNote(this,note)
            }
        }
        if (item?.itemId == R.id.action_erase) {
            noteText.text.clear()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val extra = intent.extras
        note = if (extra != null) {
            extra.get("note") as NoteModel
        } else
            NoteModel()
        noteText.setText(note.text)
        if (note.category != null && note.category?.color != null) {
            when (note.category?.color) {
                StickerColor.Orange -> noteText.background = getDrawable(R.drawable.card_view_orange)
                StickerColor.White -> noteText.background = getDrawable(R.drawable.card_view_background)
                StickerColor.Blue -> noteText.background = getDrawable(R.drawable.card_view_blue)
                StickerColor.Pink -> noteText.background = getDrawable(R.drawable.card_view_pink)
                StickerColor.Yellow -> noteText.background = getDrawable(R.drawable.card_view_yellow)
                StickerColor.Green -> noteText.background = getDrawable(R.drawable.card_view_green)
            }
        }
    }
}
