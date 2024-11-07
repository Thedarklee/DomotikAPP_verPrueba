package com.example.domotikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private EditText usuarioEditText ,rutEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vinculamos las variables a los ids
        rutEditText = findViewById(R.id.rut);
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);
        spinner = findViewById(R.id.spinnerPaises);

        //añadimos paises al listado
        String[] paises = {"Chile", "Argentina"};
        //declaramos el array y poblamos el spinner como tal
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

   //Funciones para Acceder al sistema
   public void onClickAcceder(View view) {
       MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.okok );
       //Inicio la reproducción
       mediaPlayer.start();
       String rut = rutEditText.getText().toString().trim();
       String user = usuarioEditText.getText().toString().trim();
       String pass = contrasenaEditText.getText().toString().trim();
       String pais = spinner.getSelectedItem().toString().trim();

       if (rut.isEmpty()) {
           Toast.makeText(this, "Ingrese el rut", Toast.LENGTH_SHORT).show();
           return;
       }
       if (user.isEmpty()) {
           Toast.makeText(this, "Ingrese el usuario", Toast.LENGTH_SHORT).show();
           return;
       }
       if (pass.isEmpty()) {
           Toast.makeText(this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
           return;
       }

       // Crear instancia de DataHelper y abrir la base de datos en modo lectura
       DataHelper dh = new DataHelper(this, "usuarios.db", null, 1);
       SQLiteDatabase db = dh.getReadableDatabase();
       Cursor cursor = null;
       boolean credencialesCorrectas = false;

       try {
           // Realizar consulta directa para verificar credenciales
           cursor = db.rawQuery("SELECT * FROM usuarios WHERE rut = ? AND nombre = ? AND contrasena = ? AND pais = ?",
                   new String[]{rut,user, pass, pais});

           if (cursor != null && cursor.getCount() > 0) {
               credencialesCorrectas = true;
           }
       } catch (Exception e) {
           e.printStackTrace();
           Toast.makeText(this, "Error al verificar credenciales", Toast.LENGTH_LONG).show();
       } finally {
           if (cursor != null) {
               cursor.close();
           }
           db.close();
       }

       // Verificación del resultado de credenciales
       if (credencialesCorrectas) {
           Toast.makeText(this, "Credenciales bien", Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(this, Users.class);
           startActivity(intent);
       } else {
           Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
       }
   }


    public void onClickRegistrar(View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.okok );
        //Inicio la reproducción
        mediaPlayer.start();
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }




}