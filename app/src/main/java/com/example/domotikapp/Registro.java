package com.example.domotikapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;



public class Registro  extends AppCompatActivity {
    Spinner edtspinner;
    EditText edtId, edtNom, edtCon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //vinculamos las variables a los ids
        edtspinner = findViewById(R.id.edtSpiner);
        //Defino los campos del formulario
        edtId = (EditText) findViewById(R.id.edtId);
        edtNom = (EditText) findViewById(R.id.edtNom);
        edtCon = (EditText) findViewById(R.id.edtCon);

        //añadimos paises al listado
        String[] paises = {"Chile", "Argentina"};
        //declaramos el array y poblamos el spinner como tal
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        edtspinner.setAdapter(adapter);
    }

    public void onClickAgregar(View view){
        DataHelper dh = new DataHelper(this, "usuarios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("pais", edtspinner.getSelectedItem().toString());
        reg.put("rut", edtId.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("contrasena", edtCon.getText().toString());
        MediaPlayer mediaPlayer = MediaPlayer.create(Registro.this, R.raw.okok);
        //Inicio la reproducción
        mediaPlayer.start();
        long resp = bd.insert("usuarios", null, reg);
        bd.close();
        if(resp==-1){
            Toast.makeText(this,"No se ´puede ingresar el usuario", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Registro Agregado", Toast.LENGTH_LONG).show();

        }
    }

    public void onClickModificar(View view){
        //Conexion a la BDD para manipular los registros
        DataHelper dh = new DataHelper(this, "usuarios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar
        reg.put("rut", edtId.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("contrasena", edtCon.getText().toString());
        reg.put("pais", edtspinner.getSelectedItem().toString());
        MediaPlayer mediaPlayer = MediaPlayer.create(Registro.this, R.raw.okok);
        //Inicio la reproducción
        mediaPlayer.start();

        //Actualizo el registro de la BBD por el RUT
        long respuesta = bd.update("usuarios", reg, "rut=?", new String[]{edtId.getText().toString()});
        bd.close();
        //Envio la respuesta al usuario
        if (respuesta == -1){
            Toast.makeText(this,"Usuario No Modificado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Usuario Modificado", Toast.LENGTH_LONG).show();
        }

    }
    //Metodo para Eliminar un Registro
    public void onClickEliminar(View view){
        //Conecto la BDD para eliminar un registro de tabla alumno
        DataHelper dh = new DataHelper(this, "usuarios.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        //Ingreso el rut del registro a modificar
        String RutEliminar = edtId.getText().toString();
        //Query para eliminar el registro
        long respuesta = bd.delete("usuarios", "rut=" + RutEliminar, null);
        bd.close();
        //Verifico que el registro se elimine
        if (respuesta == -1){
            Toast.makeText(this,"Usuario No Eliminado", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Usuario Eliminado", Toast.LENGTH_LONG).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(Registro.this, R.raw.basura );
            //Inicio la reproducción
            mediaPlayer.start();
        }

    }

}
