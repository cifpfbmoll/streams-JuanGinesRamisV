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

        try {//intentar abrir los documentos
            entrada = new FileInputStream(origen);
            salida = new FileOutputStream(destino);
            int i = entrada.read();

            //primero escribo la cabecera y los primeros guiones
            salida.write(escribirCabecera().getBytes());
            salida.write(guiones.getBytes());
            while (i != -1) {
                if (i == almohadilla && auxAtributos == 0) {
                    //Si es la primera almohadilla despues de una llave se
                    //como ya se ha escrito el titulo se escriben los guiones
                    //y el salto de linea
                    salida.write(guiones.getBytes());
                    salida.write(enter);
                    salida.write(enter);
                    salida.write(atributosPelicula[auxAtributos].getBytes());
                    //comprabante para no salirnos de rango nunca
                    if (auxAtributos < atributosPelicula.length - 1) {
                        auxAtributos++;
                    }
                } else if (i == almohadilla) {
                    //Si es una almohadilla salta de linea y escribe los el auxAtrib
                    salida.write(enter);
                    salida.write(enter);
                    salida.write(atributosPelicula[auxAtributos].getBytes());
                    if (auxAtributos < atributosPelicula.length - 1) {
                        auxAtributos++;
                    }
                } else if (i == llaveIzq) {
                    //si es una llaveIzq se introduce un salto de linea y se 
                    //escriben los primeros guiones
                    salida.write(enter);
                    salida.write(enter);
                    salida.write(guiones.getBytes());
                    auxAtributos = 0;
                } else {
                    //si no es almohadilla o llave se escibe el caracter
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
