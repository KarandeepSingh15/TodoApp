package com.karandeepsingh.todoapp

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.karandeepsingh.todoapp.database.NoteEntity
import com.karandeepsingh.todoapp.database.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<NoteEntity>>
    private val repository: NoteRepository

    init {
        val notesDao = NotesDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(notesDao)
        allNotes = repository.allNotes
    }

    fun deleteNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(noteEntity)

    }

    fun insertNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {

            repository.insertNote(noteEntity)

    }
}