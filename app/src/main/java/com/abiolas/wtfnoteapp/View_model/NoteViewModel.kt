package com.abiolas.wtfnoteapp.View_model
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.abiolas.wtfnoteapp.Models.NoteModel
import com.abiolas.wtfnoteapp.room.DatabaseConfig
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.abiolas.wtfnoteapp.room.AppDatabase
import kotlinx.coroutines.launch
import java.util.Calendar

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
    //Calling the save function of the database
    private var db = DatabaseConfig.getInstance(applicationn)


    private fun getStartOfDay(currentTimeMillis: Long): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = currentTimeMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.timeInMillis
    }
    fun saveNote(title: String, content: String){
        val currentTimeMillis = System.currentTimeMillis()
        if(title.isNullOrEmpty() && content.isNullOrEmpty()) return
        //Creating a Note instance
        val note = NoteModel(
            title = title,
            content = content,
            dateTime = System.currentTimeMillis(),
        )
        //Calling the save function of the database
        var db = DatabaseConfig.getInstance(applicationn)

        viewModelScope.launch {
            db.noteDao().saveNote(note)
        }
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return db.noteDao().fetchNotes()
    }

    fun getNote(noteId: String): LiveData<NoteModel>{
         return db.noteDao().fetchNote(noteId)
     }

    fun deleteNote(note: NoteModel){
        viewModelScope.launch {
            db.noteDao().deleteNote(note)
        }
    }

    fun updateNote(note: NoteModel){
        viewModelScope.launch {
            db.noteDao().updateNote(note)
        }
    }
}
