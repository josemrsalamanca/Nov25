package com.example.logingrupalapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.logingrupalapp.model.UsuarioBaseDeDatos

class LoginGrupalApplication : Application() {

    companion object{
        lateinit var DATABASE : UsuarioBaseDeDatos
    }

    override fun onCreate() {
        super.onCreate()
        initDataBase()
    }

    private fun initDataBase() {
        DATABASE = Room.databaseBuilder(this, UsuarioBaseDeDatos::class.java,"usario_database")
            .allowMainThreadQueries()
            .build()
    }
}