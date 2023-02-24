package com.aleja.appbanco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bdBanco extends SQLiteOpenHelper {
    String tblUser = "Create Table User(email text primary key, name text, password text, role integer)";
    // Se pueden definir las demás tablas para la base de datos
    public bdBanco(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table User");
        db.execSQL(tblUser);

    }
}
