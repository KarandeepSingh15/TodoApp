package com.karandeepsingh.todoapp

import androidx.lifecycle.LiveData
import com.karandeepsingh.todoapp.database.NoteDao
import com.karandeepsingh.todoapp.database.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes:LiveData<List<NoteEntity>> = noteDao.getAllNotes()
    suspend fun insertNote(noteEntity: NoteEntity)
    {
        noteDao.insertNote(noteEntity)
    }
    suspend fun deleteNote(noteEntity: NoteEntity)
    {
        noteDao.deleteNote(noteEntity)
    }
}