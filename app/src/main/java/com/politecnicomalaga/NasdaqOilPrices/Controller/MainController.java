package com.politecnicomalaga.NasdaqOilPrices.Controller;

import android.util.Log;

import com.politecnicomalaga.NasdaqOilPrices.Model.Partido;
import com.politecnicomalaga.NasdaqOilPrices.View.MainActivity;

import java.util.LinkedList;
import java.util.List;

public class MainController {

    //SINGLETON Controller
    private static final String DATA_URL = "https://www.loteriasyapuestas.es/es/la-quiniela/escrutinios/la-quiniela-premios-y-ganadores-del-22-de-octubre-de-2023";
    private static MainController mySingleController;

    private List<Partido> dataRequested;



    private static MainActivity activeActivity;
    //Comportamiento
    //Constructor
    private MainController() {
         dataRequested = new LinkedList<Partido>();
    }

    //Get instance
    public static MainController getSingleton() {
        if (MainController.mySingleController == null) {
            mySingleController = new MainController();
        }
        return mySingleController;
    }

    //To send data to view
    public List<Partido> getData() {
        return this.dataRequested;
    }

    //Called from the view
    public void requestData() {

        Peticion p = new Peticion();
        p.requestData(DATA_URL);
    }


    //Called when onResponse is OK
    public void setDataFromNasdaq(String json) {

        Respuesta answer = new Respuesta(json);
        dataRequested = answer.getData();
        // Imprimir datos para verificar
       /* for (Partido partido : dataRequested) {
            Log.d("DataDebug", "Equipo1: " + partido.getEquipo1() +
                    ", Equipo2: " + partido.getEquipo2() +
                    ", Resultado: " + partido.getResultado());
        }*/
        //Load data on the list
        MainController.activeActivity.accessData();
    }

    public void setErrorFromNasdaq(String error) {

        //Load data on the list
        MainController.activeActivity.errorData(error);
    }


    public static void setActivity(MainActivity myAct) {
        activeActivity = myAct;
    }

}
