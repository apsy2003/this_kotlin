package com.apsy2003.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ToDoEntity::class),version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao() : ToDoDao
    companion object {
        val databaseName = "db_todo" // 데이터베이스 이름. 임의로 지정해주어도 된다.
        var appDatabase : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase? {
            if(appDatabase == null){
                appDatabase =  Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    databaseName).
                fallbackToDestructiveMigration()
                    .build()
            }
            return  appDatabase
        }
    }
}