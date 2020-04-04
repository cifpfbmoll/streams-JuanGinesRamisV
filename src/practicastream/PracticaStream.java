/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
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
            int opcionMenu2;
            imprimirMenu();
            opcion = lector.nextInt();

            switch (opcion) {
                case 1:
                    ByteStreams.escribirDocumento(pedirRutaOrigen(), pedirRutaDestino());
                    break;
                case 2:
                    CharStreams.escribirDocumento(pedirRutaOrigen(), pedirRutaDestino());
                    break;
                case 3:
                    CharBufferStreams.escribirDocumento(pedirRutaOrigen(), pedirRutaDestino());
                    break;
                case 4:
                    imprimirSubMenu();
                    opcionMenu2= lector.nextInt();
                    switch (opcionMenu2){
                        case 1:
                            CharBufferStreams.leerLineaEscribirObjeto(pedirRutaOrigen(), pedirRutaDestino());
                            break;
                        case 2: 
                            CharBufferStreams.leerObjetosEscribirObjetos(pedirRutaOrigen(), pedirRutaDestino());
                            break;
                        case 3:
                            CharBufferStreams.leerObjetosConsola(pedirRutaOrigen());
                            break;
                    }
                    break;
                case 5:
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
        System.out.println("4)Tratamiento de objetos.");
        System.out.println("5) salir");
        System.out.println("---------------");
        System.out.print("Selecionna una opcion: ");
    }
    
    public static void imprimirSubMenu(){
        System.out.println("1) Lectura línea a línea y escritura con objetos");
        System.out.println("2) Lectura de objetos y escritura de objetos.");
        System.out.println("3) Lectura de objetos y escritura por consola.");
        System.out.println("4) Volver al menú principal.");
        System.out.println("-------------------------------------");
        System.out.print("Selecciona una opcion: ");
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
                String mensajeError = "Se ha introducido una ruta invalida introduce"
                        + " una ruta de nuevo";
                System.out.println(mensajeError);
                try {
                    Date fechaActual = new Date();
                    FileWriter errores = new FileWriter("errores.txt", true);
                    errores.write(fechaActual.toString());
                    errores.write(13);
                    errores.write(mensajeError);
                    errores.write(13);//enter
                    PrintWriter erroresToLog = new PrintWriter(errores);
                    exc.printStackTrace(erroresToLog);
                    errores.write(13);//enter
                    erroresToLog.close();
                    errores.close();
                } catch (IOException ex) {
                    System.out.println("No se encuentra el archivo de errores");
                }
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
                String mensajeError="No has introducido una ruta";
                System.out.println(mensajeError);
                entrada=lector.nextLine();
                try {
                    Date fechaActual = new Date();
                    FileWriter errores = new FileWriter("errores.txt", true);
                    errores.write(fechaActual.toString());
                    errores.write(13);//enter
                    errores.write(mensajeError);
                    errores.write(13);//enter
                    PrintWriter erroresToLog = new PrintWriter(errores);
                    sal.printStackTrace(erroresToLog);
                    errores.write(13);//enter
                    erroresToLog.close();
                    errores.close();
                } catch (IOException ex) {
                    System.out.println("No se ha enconrado un archivo de errores");
                    ex.printStackTrace();
                }

            }
        }
        return entrada;
    }
}
