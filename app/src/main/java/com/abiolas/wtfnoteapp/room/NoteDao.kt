package com.abiolas.wtfnoteapp.room

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abiolas.wtfnoteapp.Models.NoteModel


@Dao
interface NoteDao {
    @Insert (onConflict = OnConflictStrategy.ABORT)
    suspend fun saveNote(note:NoteModel)

    @Query("select * from notes order BY id DESC")
    fun fetchNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun fetchNote(noteId: String): LiveData<NoteModel>

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)
}


