package com.example.domotikapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent; // Para usar Intents
import android.os.Bundle; // Para manejar el ciclo de vida de la actividad
import android.view.Gravity; // Para centrar elementos en la tabla
import android.view.View; // Para manejar eventos de vista
import android.widget.ArrayAdapter; // Para adaptar listas (aunque no lo usamos aquí)
import android.widget.Button; // Para botones
import android.widget.ImageView; // Para mostrar imágenes
import android.widget.RadioButton; // Para botones de opción
import android.widget.TableLayout; // Para usar TableLayout
import android.widget.TableRow; // Para usar TableRow
import android.widget.TextView; // Para mostrar texto
import android.widget.Toast; // Para mostrar mensajes emergentes
import androidx.appcompat.app.AppCompatActivity; // Para extender la clase AppCompatActivity
import android.database.Cursor; // Para trabajar con los resultados de la base de datos
import android.database.sqlite.SQLiteDatabase; // Para manejar la base de datos SQLite

import androidx.appcompat.app.AppCompatActivity;

public class Users extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        CargarLista();
    }

    public void onClickFuncion (View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(Users.this, R.raw.okok );
        //Inicio la reproducción
        mediaPlayer.start();
        Intent intent = new Intent(this, Items.class);

        startActivity(intent);

    }
    public void onClickAccederTuto (View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(Users.this, R.raw.okok );
        //Inicio la reproducción
        mediaPlayer.start();
        Intent intent = new Intent(this, Tuto.class);

        startActivity(intent);

    }



    public void CargarLista(){
        DataHelper dh = new DataHelper(this, "usuarios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("SELECT rut, nombre, contrasena, pais FROM usuarios", null);

        TableLayout tableLayout = findViewById(R.id.tableLayout); // Asegúrate de que este es el ID de tu TableLayout

        // Limpiar el TableLayout antes de cargar nuevos datos
        tableLayout.removeAllViews();

        // Crear encabezados
        TableRow headerRow = new TableRow(this);
        String[] headers = {"ID", "Nombre", "Imagen", "Función", "Item"};
        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setPadding(8, 8, 8, 8);
            textView.setGravity(Gravity.CENTER);
            headerRow.addView(textView);
        }
        tableLayout.addView(headerRow);

        if (c.moveToFirst()) {
            do {
                TableRow row = new TableRow(this);
                // ID
                TextView idTextView = new TextView(this);
                idTextView.setText(c.getString(0)); // rut
                idTextView.setPadding(8, 8, 8, 8);
                idTextView.setGravity(Gravity.CENTER);
                row.addView(idTextView);

                // Nombre
                TextView nombreTextView = new TextView(this);
                nombreTextView.setText(c.getString(1)); // nombre
                nombreTextView.setPadding(8, 8, 8, 8);
                nombreTextView.setGravity(Gravity.CENTER);
                row.addView(nombreTextView);

                // Imagen
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new TableRow.LayoutParams(50, 50));
                imageView.setImageResource(R.drawable.icon); // Cambia por el recurso correspondiente
                row.addView(imageView);

                // Función (RadioButton)
                RadioButton radioButton = new RadioButton(this);
                radioButton.setId(View.generateViewId()); // Generar un ID único
                radioButton.setText("Inactivo");
                row.addView(radioButton);

                // Botón de ingresar
                Button ingresarButton = new Button(this);
                ingresarButton.setText("Ingresar");
                ingresarButton.setTextColor(getResources().getColor(R.color.white)); // Cambia por el color correspondiente
                ingresarButton.setOnClickListener(v -> onClickFuncion(v));
                row.addView(ingresarButton);

                tableLayout.addView(row);
            } while (c.moveToNext());
        } else {
            Toast.makeText(this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
        }

        // Cerrar el cursor y la base de datos después de usarlos
        c.close();
        bd.close();
    }

}
