/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author juang
 */
public abstract class CharStreams {

    public static void escribirDocumento(String origen, String salida) {
        //declaracion de constantes de la tabla ASCII para facilitar la codificación
        final int almohadilla = 35;
        final int llaveIzq = 123;
        final int enter = 13;
        final String guiones = "---------";
        //declaracion de la costante "atributos" que usare para impirmir los 
        //atributos de la pelicula como genero: director: etc...
        final String[] atributosPelicula = {"año: ", "Director: ", "Duración: ",
            "Sinopsis: ", "Reparto: ", "Sesión: "};
        int auxAtributos = 0; //para poder moverme por el string usare esta variable

        try (FileReader lector = new FileReader(origen);
                FileWriter escritor = new FileWriter(salida)) {
            int caracter = lector.read();
            escritor.write(escribirCabecera());
            escritor.write(guiones);
            while (caracter != -1) {
                if (caracter == almohadilla && auxAtributos == 0) {
                    escritor.write(guiones);
                    escritor.write(enter);
                    escritor.write(enter);
                    escritor.write(atributosPelicula[auxAtributos]);
                    auxAtributos++;//como siempre sera cero en este momento
                } else if (caracter == almohadilla) {//no hace falta comporbar que sea menor para no
                    //salirnos
                    escritor.write(enter);
                    escritor.write(enter);
                    escritor.write(atributosPelicula[auxAtributos]);
                    if (auxAtributos < atributosPelicula.length - 1) {
                        auxAtributos++;
                    }
                } else if (caracter == llaveIzq) {
                    escritor.write(enter);
                    escritor.write(enter);
                    escritor.write(guiones);
                    auxAtributos = 0;
                } else {
                    escritor.write(caracter);
                }
                caracter = lector.read();
            }
        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public static String escribirCabecera() {
        String cabecera = "--------------------------------------\n"
                + "Cartelera de CineFBMoll \n---------------------------------"
                + "----- \n \n";
        return cabecera;
    }
}
