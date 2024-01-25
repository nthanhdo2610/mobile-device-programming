package com.bright.sunriseset.dao

import androidx.room.*
import com.bright.sunriseset.entity.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)
    @Query("SELECT * FROM NOTE ORDER BY id DESC")
    suspend fun getAllNotes():List<Note>
    @Insert
    suspend fun addMultipleNotes(vararg note: Note)
    @Update
    suspend fun updateNote(note:Note)
    @Delete
    suspend fun deleteNote(note: Note)
}