package com.karandeepsingh.todoapp.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note:NoteEntity)
    @Delete
    suspend fun deleteNote(note:NoteEntity)
    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<NoteEntity>>

}