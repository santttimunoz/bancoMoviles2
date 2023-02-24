package com.aleja.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Instanciar la clase bdBanco para los diferentes botones (crud)
    bdBanco Banco = new bdBanco(this,"bancodb",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referenciar los objetos instanciados
        EditText email = findViewById(R.id.etMail);
        EditText name = findViewById(R.id.etName);
        EditText password = findViewById(R.id.etPassword);
        RadioButton user = findViewById(R.id.rbUser);
        RadioButton admin = findViewById(R.id.rbAdmin);
        Button create = findViewById(R.id.btnCreate);
        Button search = findViewById(R.id.btnSearch);
        Button edit = findViewById(R.id.btnEdit);
        Button sign = findViewById(R.id.btnSign);
        Button delete = findViewById(R.id.btnDelete);
        Button list = findViewById(R.id.btnList);
        // Evento de guardar
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    // Buscar el email en la tabla user
                    SQLiteDatabase sdBancoread = Banco.getReadableDatabase();
                    String query = "Select email From User Where email = '"+email.getText().toString()+"'";
                    Cursor cUser = sdBancoread.rawQuery(query, null);
                    if (!cUser.moveToFirst()){ // No encuentra el email ingresado
                        // Instanciar la clase de SQLiteDatabase en modo escritura
                        SQLiteDatabase sdBanco = Banco.getWritableDatabase();
                        // Contenedor de valores
                        ContentValues cvUser = new ContentValues();
                        cvUser.put("email", email.getText().toString());
                        cvUser.put("name", name.getText().toString());
                        cvUser.put("password", password.getText().toString());
                        cvUser.put("role", user.isChecked() ? 0 : 1);
                        sdBanco.insert("User",null,cvUser);
                        sdBanco.close();
                        Toast.makeText(getApplicationContext(),"Usuario guardado correctamente...",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Usuario registrado, Intente con otro email...",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Debe ingresar todos los datos...",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Evento click para listado de usuarios
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pasar a la actividad que muestra los usuarios
                startActivity(new Intent(getApplicationContext(),listUsers.class));
            }
        });

    }
}