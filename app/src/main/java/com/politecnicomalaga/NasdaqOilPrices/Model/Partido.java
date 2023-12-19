package com.politecnicomalaga.NasdaqOilPrices.Model;

public class Partido {

    //Aunque sabemos que precios es un double, lo guardamos en String porque nos llega en String
    //y se muestra como texto, al fin y al cabo

    //POJO CLASS
    private String equipo1;
    private String equipo2;
    private String resultado;

    //Comportamiento
    //Construtor

    public Partido(String equipo1, String equipo2, String resultado) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resultado = resultado;
    }


    //Getters


    public String getEquipo1(){
        return this.equipo1;
    }
    public String getEquipo2(){
        return this.equipo2;
    }
    public String getResultado(){
        return this.resultado;
    }
}
