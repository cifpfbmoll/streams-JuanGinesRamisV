/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juang
 */
public class PracticaStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //----------------
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        while (salir == false) {
            int opcion;
            imprimirMenu();
            opcion = lector.nextInt();

            switch (opcion) {
                case 1:
                    ByteStreams.escribirDocumento(pedirRutaOrigen(), pedirRutaDestino());
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    salir = true;
                    break;
            }
        }
    }

    public static void imprimirMenu() {
        System.out.println("");
        System.out.println("1)Lectura y escritura de ficheros de cartelera"
                + "byte a byte (byte Streams");
        System.out.println("2) Lectura y escritura de fichero de cartelera cará"
                + "cter a carácter (chareacter Streams");
        System.out.println("3) Lectura y escritura de fichero de fichero línea"
                + "a línea (Character Streams)");
        System.out.println("---------------");
        System.out.print("Selecionna una opcion: ");
    }

    public static String pedirRutaOrigen() {
        //en este metodo solo verificare si la ruta es valida es decir que tenga
        //un punto por ejemplo "origen.txt" seria valido.
        Scanner lector = new Scanner(System.in);
        String entrada = "";
        System.out.println("Introduce la ruta de origen");
        try {
            entrada = lector.next();
            if (entrada.contains(".") == false) {
                throw new ExcepcionRutaInvalida();
            }
        } catch (ExcepcionRutaInvalida exc) {
            while (entrada.contains(".") == false) {
                System.out.println("Se ha introducido una ruta invalida introduce"
                        + "una ruta de nuevo");
                entrada = lector.next();
            }
        }
        return entrada;
    }

    public static String pedirRutaDestino() {
        Scanner lector = new Scanner(System.in);
        String entrada = "";
        
        System.out.println("Introduce la ruta del destino de salida");
        entrada = lector.nextLine();
        try {
            if (entrada.length() == 0) {
                throw new ExcepcionRutaSalida();
            }
        } catch (ExcepcionRutaSalida sal) {
            while (entrada.length() == 0) {
                System.out.println("Ruta no introducida introducela de nuevo");
                entrada = lector.nextLine();
            }
        }
        return entrada;
    }
}
