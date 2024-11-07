package com.example.domotikapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//importo las librerias necesarias
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.preference.PreferenceManager; // Importa PreferenceManager para gestionar las preferencias de la aplicación.
import org.osmdroid.config.Configuration; // Importa la clase Configuration para configurar el mapa.
import org.osmdroid.tileprovider.tilesource.TileSourceFactory; // Importa TileSourceFactory para definir los tipos de mapas disponibles.
import org.osmdroid.util.GeoPoint; // Importa GeoPoint, que representa coordenadas geográficas (latitud y longitud).
import org.osmdroid.views.MapView; // Importa MapView, que es el componente visual del mapa.
import org.osmdroid.views.overlay.Marker; // Importa Marker para agregar marcadores en el mapa.
import org.osmdroid.views.overlay.Polyline; // Importa Polyline para dibujar líneas en el mapa.


public class Maplando extends AppCompatActivity {

    //Asigno variable privada para la clase
    private TextView textView;
    private ProgressBar progressBar;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplando);

        //Enlazamos las variables con su Ids
        progressBar = findViewById(R.id.progressBarMap);
        mapView = findViewById(R.id.mapViewlando);
        textView = findViewById(R.id.textoCargaMapa);

        //Cargar config del mapa utilizando las preferencias predeterminadas
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        //MapView mapView = findViewById(R.id.mapViewlando);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        //agregamos Latitud y Longitud de las ubicaciones
        double ipSantoTomasLatitud = -33.4493141;
        double ipSantoTomasLongitud = -70.6624069;
        double metroHLatitud = -33.44615;
        double metroHLongitud = -70.66038;

        //Creamos los GeoPoints
        GeoPoint IPsantoTomasPoint = new GeoPoint(ipSantoTomasLatitud, ipSantoTomasLongitud);
        GeoPoint metroHPoint = new GeoPoint(metroHLatitud, metroHLongitud);

        //Le asignamos al mapa un zoom predeterminado al inicio
        mapView.getController().setZoom(15.0);
        //centramos en mapa en un punto especifico
        mapView.getController().setCenter(IPsantoTomasPoint);

        //Creo un marcador para el ip st tomas
        Marker marcadorST = new Marker(mapView);
        //Creo un marcador para el metro
        Marker marcadormetroH = new Marker(mapView);

        //Creo el marcador en el instituto
        marcadorST.setPosition(IPsantoTomasPoint);
        marcadorST.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        marcadorST.setTitle("Ip tanto tomar");
        marcadorST.setSnippet("instituto");
        mapView.getOverlays().add(marcadorST);


        //Creo el marcador en el metro
        marcadormetroH.setPosition(metroHPoint);
        marcadormetroH.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        marcadormetroH.setTitle("metro");
        marcadormetroH.setSnippet("heroes");
        mapView.getOverlays().add(marcadormetroH);


        //Agrego una linea entre ambos marcadores
        Polyline linea = new Polyline();
        linea.addPoint(IPsantoTomasPoint);
        linea.addPoint(metroHPoint);
        linea.setColor(0XFF0000FF); //ARGB
        linea.setWidth(5);
        //Añado la linea al mapa
        mapView.getOverlayManager().add(linea);

        //creamos nuestro Thread para simular la carga de Mapa
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //SIMULAMOS EL TIEMPO DE CARGA DE LA OPERACION
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Actualizamos la vista de usuario(UI) desde el hilo principal cuando pasen 2 seg
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        //Actualizar texto
                        textView.setText("Mapa cargado correctamente");
                        //Hacemos visible el mapa
                        mapView.setVisibility(View.VISIBLE);

                    }
                });

            }

        });
        //iniciamos el hilo
        thread.start();
    }
}














