package com.karandeepsingh.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        var Instance: NotesDatabase? = null
        fun getDatabase(context: Context): NotesDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
                Instance=instance
                instance
            }

        }
    }
}