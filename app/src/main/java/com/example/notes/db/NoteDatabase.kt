package com.example.notes.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract  class NoteDatabase : RoomDatabase(){
      abstract  fun getNoteDao () : NoteDao

      companion object{
          @Volatile private var instance : NoteDatabase? = null
          private val LOCK = Any()

          operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
              instance ?: buildDatabase(context).also{
                  instance = it
              }
          }

          private fun buildDatabase(context: Context) = Room.databaseBuilder(
              context.applicationContext,
              NoteDatabase::class.java,
              "notedatabase"
          ).build()
      }
  }