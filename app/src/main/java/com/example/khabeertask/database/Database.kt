package com.example.khabeertask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.khabeertask.database.dao.PayrollDao
import com.example.khabeertask.database.model.PayrollRoom

@Database(entities = [PayrollRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    abstract val payrollDao: PayrollDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase"
            ).build()
        }
    }
    return INSTANCE
}
