package com.aleja.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class listUsers extends AppCompatActivity {
    ListView listUsers;
    ArrayList<String> arrUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        listUsers = findViewById(R.id.lvUsers);
        Button btnBack = findViewById(R.id.btnback);
        loadUsers();
    }

    private void loadUsers() {
        arrUsers = retrieveUsers();


    }

    private ArrayList<String> retrieveUsers() {
        // Cargar los usuarios en el arraylist arrUsers;
        bdBanco sohdbBanco = new bdBanco(getApplicationContext(),"bancodb",null,1);
        SQLiteDatabase dbBancoread = sohdbBanco.getReadableDatabase();
        String qAllUsers = "Select email, name, password, role From User";
        Cursor cUsers = dbBancoread.rawQuery(qAllUsers,null);
        if (cUsers.moveToFirst()){
            do{
                // Generar un string para almacenar toda la información de cada usuario
                // y guardarlo en el arrayList
            }while(cUsers.moveToNext());
        }
    }
}