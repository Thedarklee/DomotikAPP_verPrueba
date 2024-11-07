package com.example.domotikapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;


public class DataHelper extends SQLiteOpenHelper{
    public  DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE usuarios(rut INTEGER PRIMARY KEY, nombre TEXT, contrasena TEXT, pais TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("CREATE TABLE usuarios(rut INTEGER PRIMARY KEY, nombre TEXT, contrasena TEXT, pais TEXT)");
    }


}
