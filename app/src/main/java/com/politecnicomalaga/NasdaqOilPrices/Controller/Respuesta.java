package com.politecnicomalaga.NasdaqOilPrices.Controller;


import com.politecnicomalaga.NasdaqOilPrices.Model.Partido;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase respuesta. Encapsulará los datos que nos devuelve la API REST
 * opendata de Nasdaq.
 *
 * El controlador le dará el texto a "analizar" en JSON y proporcionará
 * una serialización de los datos "amigable" para la vista. Es en
 * realidad un procesador de textos (parser)
 */

public class Respuesta {
    //ESTADO
    protected String datos;
    //COMPORTAMIENTO
    public Respuesta(String entrada) {
        datos = entrada;
    }


    public List<Partido> getData() {
        LinkedList<Partido> dataList = new LinkedList<>();
        // Parsing the HTML
        try {
            String parsear = encontrarMetaDescription(datos);
            //tengo en parsear <meta description...>

            //AQUI YA ESTÁN LOS 15 RESULTADOS
            dataList=encontrarContenidoDescripcion(parsear);


            //analizamos toda la información

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;

    }
    /*<meta name="description" content="Los resultados correspondientes a la jornada de La Quiniela,
    del 22 de octubre de 2023, son los siguientes: 1 Real Sociedad - Mallorca 1 2 Getafe - Betis X 3
    Sevilla - Real Madrid X 4 Celta - At. Madrid 2 5 Las Palmas - Rayo Vallecano 2 6 Girona -
    Almería 1 7 Villarreal - Alavés X 8 Barcelona - Athletic Club 1 9 Tenerife - Levante X 10 R.
    Zaragoza - Eibar 2 11 Huesca - Elche 2 12 Albacete - Sporting 2 13 Racing - Burgos 1 14
    Valladolid - Andorra 1 Pleno al 15 Valencia - Cádiz 2 - 0 El reparto de premios queda como sigue:
    Categoría Acertantes Premios Pleno al 15 3 705.308,49 € 1ª (14 Aciertos) 16 39.649,92 € 2ª
    (13 Aciertos) 509 584,23 € 3ª (12 Aciertos) 6.228 47,75 € 4ª (11 Aciertos) 42.168 7,05 € 5ª
    (10 Aciertos) 179.220 1,99 € Elige8 Acertantes Premio 134,03 € 440">
    */


    private String encontrarMetaDescription(String html) {
        String res = "";
        String[] division = html.split("<meta name=\"description\" content=\"");

        String fin=division[1];

        // Divide el string en 2, antes y después de encontrar meta name description
        // Me quedo con la segunda parte, pues es la que tiene meta name description
        // La primera aparición de > es cuando acaba toda esa línea
        //se queda con la segunda, hasta el final de la primera aparición de >

        int endIndex = fin.indexOf(">");
        res= fin.substring(0, endIndex + 1);



        return res;
    }
    private LinkedList encontrarContenidoDescripcion(String html) {
        LinkedList<Partido> datosDepurados = new LinkedList<>();

        int startIndex = html.indexOf("siguientes:");
        String contenido = html.substring(startIndex + "siguientes:".length());

        int endIndex = contenido.indexOf("El reparto") - 10;
        contenido = contenido.replace("Pleno al ", "");

        String temp = contenido.substring(0, endIndex).trim();

        // Split y procesamiento
        for (int i = 1; i <= 14; i++) {
            int empieza = temp.indexOf(String.valueOf(i));
            int acaba = temp.indexOf(String.valueOf(i + 1));

            if (empieza != -1 && acaba != -1 && acaba > empieza) {
                //lineaejemplo -> 2 Getafe - Betis X
                String linea = temp.substring(empieza, acaba).trim();
                //Desde después del úmero hasta que encuentra primer -
                String equipo1 = linea.substring(2,linea.indexOf(" - ")).trim();
                //Desde que encuentra - hasta antes del último espacio
                String equipo2 = linea.substring(linea.indexOf(" - ")+2,linea.lastIndexOf(" ")).trim();
                // Desde el último espacio hasta el final
                String resultado = linea.substring(linea.lastIndexOf(" ")+1, linea.length());
                datosDepurados.add(new Partido(equipo1,equipo2,resultado));
            }
        }
        String linea15 = temp.substring(temp.indexOf(String.valueOf(15)), temp.length());
        String equipo1 = linea15.substring(2,linea15.indexOf(" - ")).trim();
        String equipo2 = linea15.substring(linea15.indexOf(" - ")+2,linea15.lastIndexOf(" ")-3).trim();
        String resultado = linea15.substring(linea15.lastIndexOf(" ")-3, linea15.length());
        datosDepurados.add(new Partido(equipo1,equipo2,resultado));

        return datosDepurados;
    }

}
