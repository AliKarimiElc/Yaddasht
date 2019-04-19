package com.example.yaddasht

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.yaddasht.model.CategoryModel
import com.example.yaddasht.model.adapter.NoteListAdapter
import com.example.yaddasht.model.NoteModel
import com.example.yaddasht.model.StickerColor
import com.example.yaddasht.service.NoteService
import com.example.yaddasht.service.NoteSort
import com.example.yaddasht.utilities.CustomToast
import com.example.yaddasht.utilities.database.ApplicationRoom
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NoteListAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private var noteList: List<NoteModel>? = null
    private var sorting = NoteSort.DateDec

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_add_note) {
            startActivity(Intent(this@MainActivity,NoteActivity::class.java))
        }
        if(item?.itemId == R.id.action_sort)
        {
            val colors = arrayOf("زمان نزولی", "زمان صعوی", "رنگ")
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("مرتب سازی بر اساس")
                .setCancelable(true)
            alertDialogBuilder.setItems(colors, DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    0 -> NoteService.noteSorting = NoteSort.DateDec
                    1 -> NoteService.noteSorting = NoteSort.DateAsc
                    2 -> NoteService.noteSorting = NoteSort.Color
                }
                initList(NoteService.noteSorting)
            })
            val dialog = alertDialogBuilder.create()
            dialog.show()
        }
        if(item?.itemId == R.id.action_exit)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "یادداشت"
    }

    override fun onResume() {
        super.onResume()
        initList(NoteService.noteSorting)
    }


    fun initList(sorting:NoteSort){
        noteList = NoteService.getNoteList(this,sorting)
        adapter = NoteListAdapter(noteList!!, this)
        gridLayoutManager = GridLayoutManager(this, 2)
        changeLayoutManager()
    }



    private fun changeLayoutManager() {
        noteRecycler.layoutManager = gridLayoutManager
        noteRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
