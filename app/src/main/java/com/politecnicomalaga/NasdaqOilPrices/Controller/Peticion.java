package com.politecnicomalaga.NasdaqOilPrices.Controller;

import android.os.Handler;
import android.os.Looper;

import com.politecnicomalaga.NasdaqOilPrices.Controller.MainController;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Peticion
 *
 * Es utilizado por el controlador. El controlador le proporciona
 * los datos necesarios
 *
 * Se apoyará en OkHttp (librería cliente http/http2)
 *
 */
public class Peticion {
    //ESTADO
    //Clase utilidad que no necesita nada más que poner a funcionar la peticion HTTPs
    //private static final String API_KEY = "&api_key=Uw6L_hHx7DdsKzcpaK6H";
    //COMPORTAMIENTO
    public Peticion() {

    }

    public void requestData(String URL) {
        OkHttpClient cliente = null;
        Request peticion = null;
        try {
            //La clase para hacer peticion en internet
            cliente = new OkHttpClient();

            //construimos la peticion
            peticion = new Request.Builder()
                    .url(URL)
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //realizamos la llamada al server, pero en otro thread (con enqueue)
        Call llamada = cliente.newCall(peticion);
        llamada.enqueue(new Callback() {
            public void onResponse(Call call, Response respuestaServer)
                    throws IOException {
                //Got answer!!!!!
                String respuesta = respuestaServer.body().string();
                // Create a handler that associated with Looper of the main thread
                Handler manejador = new Handler(Looper.getMainLooper());
                // Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread
                        MainController.getSingleton().setDataFromNasdaq(respuesta);
                    }
                });
            }

            public void onFailure(Call call, IOException e) {
                String respuesta = e.getMessage();
                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread
                        MainController.getSingleton().setDataFromNasdaq("");
                        MainController.getSingleton().setErrorFromNasdaq(respuesta);
                    }
                });
            }
        });


    }

}
