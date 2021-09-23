package com.karandeepsingh.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karandeepsingh.todoapp.adapter.NotesAdapter
import com.karandeepsingh.todoapp.database.NoteEntity

class MainActivity : AppCompatActivity(), NotesAdapter.Listener {
    lateinit var recyclerView: RecyclerView
    lateinit var etEnterNote:EditText
    lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter:NotesAdapter
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etEnterNote=findViewById(R.id.etEnterNote)
        recyclerView=findViewById(R.id.RecyclerView)
        recyclerLayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=recyclerLayoutManager
        recyclerAdapter=NotesAdapter(this,this)
        recyclerView.adapter=recyclerAdapter

        noteViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, Observer {list->
            list?.let {
                recyclerAdapter.onUpdate(it)
            }






        })


    }

    override fun onClick(noteEntity: NoteEntity) {
        noteViewModel.deleteNote(noteEntity)
        Toast.makeText(this,"${noteEntity.text} removed",Toast.LENGTH_SHORT).show()

    }

    fun AddTodo(view: android.view.View) {
        val text=etEnterNote.text.toString()
        if(text.isNotEmpty())
        {
            noteViewModel.insertNote(NoteEntity(text))
            Toast.makeText(this,"$text added",Toast.LENGTH_SHORT).show()
        }

    }
}