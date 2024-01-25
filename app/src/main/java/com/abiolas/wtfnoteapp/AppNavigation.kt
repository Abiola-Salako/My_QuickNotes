package com.abiolas.wtfnoteapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abiolas.wtfnoteapp.screens.AddNoteScreen
import com.abiolas.wtfnoteapp.screens.NoteDetailsScreen
import com.abiolas.wtfnoteapp.screens.NoteListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "note-list"

    ){
        composable( Routes.NoteListRoute){
            NoteListScreen(navController)
        }

        composable(Routes.AddNoteRoute){
            AddNoteScreen(navController)
        }


        composable("note-details/{noteId}"){
            NoteDetailsScreen(
                navController = navController,
                noteId =it.arguments!!.getString("noteId")!!
            )
        }

    }
}

object Routes{ val  NoteListRoute = "note-list"
               val  AddNoteRoute = "add-note"
               fun  NoteDetails (noteId:String):String{
                    return "note-details/$noteId"
        }
}
