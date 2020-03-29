/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author juang
 */
public class CharBufferStreams {

    public static void escribirDocumento(String origen, String destino) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(origen));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(destino));
            final String[] atributosPelicula = {"año: ", "Director: ", "Duración: ",
                "Sinopsis: ", "Reparto: ", "Sesión: "};
            String origenString = lector.readLine();
            String[] peliculas = origenString.split("[{]");//lista de peliculas con atributos
            String[] datosPelicula = null;//almacenar todos los atributos de la pelicula

            escritor.write(escribirCabecera());
            for (int i = 0; i < peliculas.length; i++) {
                datosPelicula = peliculas[i].split("#");
                for (int j = 0; j < datosPelicula.length; j++) {
                    if (j == 0) {
                        escritor.write("---------" + datosPelicula[j] + "------"
                                + "---");
                        escritor.newLine();
                        escritor.newLine();
                    } else{
                        escritor.write(atributosPelicula[j - 1] + datosPelicula[j]);
                        escritor.newLine();
                        escritor.newLine();
                    }
                }
            }
            lector.close();
            escritor.close();
        } catch (IOException ex) {
            System.out.println("hola");
        }
    }

    public static String escribirCabecera() {
        String cabecera = "--------------------------------------\n"
                + "Cartelera de CineFBMoll \n---------------------------------"
                + "----- \n \n";
        return cabecera;
    }
}
