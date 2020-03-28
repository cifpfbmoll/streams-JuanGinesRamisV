/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juang
 */
public abstract class ByteStreams {

    public static void escribirDocumento(String origen, String destino) {
        //Declaracion de costantes del codigo ASCII 
        final int almohadilla = 35;
        final int llaveIzq = 123;
        final int enter = 13;
        final String guiones = "---------";
        //declaracion de la costante "atributos" que usare para impirmir los 
        //atributos de la pelicula como genero: director: etc...
        final String[] atributosPelicula = {"año: ", "Director: ", "Duración: ",
            "Sinopsis: ", "Reparto: ", "Sesión: "};
        int auxAtributos = 0; //para poder moverme por el string usare esta variable

        FileInputStream entrada = null;
        FileOutputStream salida = null;

        try {
            entrada = new FileInputStream(origen);
            salida = new FileOutputStream(destino);
            int i = entrada.read();

            //primero escribo la cabecera
            salida.write(escribirCabecera().getBytes());
            salida.write(guiones.getBytes());
            while (i != -1) {
                if (i == almohadilla && auxAtributos == 0) {
                    salida.write(guiones.getBytes());
                    salida.write(13);
                    salida.write(atributosPelicula[auxAtributos].getBytes());
                    if (auxAtributos < atributosPelicula.length - 1) {
                        auxAtributos++;
                    }
                } else if (i == almohadilla) {
                    salida.write(13);
                    salida.write(atributosPelicula[auxAtributos].getBytes());
                    if (auxAtributos < atributosPelicula.length - 1) {
                        auxAtributos++;
                    }
                } else if (i == llaveIzq) {
                    salida.write(enter);
                    salida.write(enter);
                    salida.write(guiones.getBytes());
                    auxAtributos = 0;
                } else {
                    salida.write(i);
                }
                i = entrada.read();

            }

        } catch (IOException ex) {
            Logger.getLogger(ByteStreams.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero de origen");
            }

            try {
                if (destino != null) {
                    salida.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero de salida");
            }
        }
    }

    public static String escribirCabecera() {
        String cabecera = "--------------------------------------\n"
                + "Cartelera de CineFBMoll \n---------------------------------"
                + "----- \n \n";
        return cabecera;
    }

}
