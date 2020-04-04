/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    } else {
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

    public static void leerLineaEscribirObjeto(String rutaOrigen, String rutaDestino) {
        try (FileOutputStream destino = new FileOutputStream(rutaDestino);
                ObjectOutputStream escritorObjetos = new ObjectOutputStream(destino);
                BufferedReader lector = new BufferedReader(new FileReader(rutaOrigen));) {

            String origenString = lector.readLine();
            String[] peliculas = origenString.split("[{]");
            String[] datosPeliculas = null;
            for (int i = 0; i < peliculas.length; i++) {
                datosPeliculas = peliculas[i].split("#");
                escritorObjetos.writeObject(new Cartelera(
                        datosPeliculas[0], datosPeliculas[1], datosPeliculas[2],
                        datosPeliculas[3], datosPeliculas[4], datosPeliculas[5],
                        datosPeliculas[6]));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CharBufferStreams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CharBufferStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerObjetosEscribirObjetos(String origen, String destino) {
        try (FileInputStream entradaObj = new FileInputStream(origen);
                ObjectInputStream lectorObjetos = new ObjectInputStream(entradaObj);
                FileOutputStream salidaObj = new FileOutputStream(destino);
                ObjectOutputStream escritorObj = new ObjectOutputStream(salidaObj)) {

            while (true) {
                Cartelera p = (Cartelera) lectorObjetos.readObject();
                escritorObj.writeObject(p);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CharBufferStreams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Fin del fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CharBufferStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerObjetosConsola(String origen) {
        try (FileInputStream entradaOjb = new FileInputStream(origen);
                ObjectInputStream lectorObjetos = new ObjectInputStream(entradaOjb)) {
            
            while(true){
                Cartelera aux = (Cartelera)lectorObjetos.readObject();
                System.out.println("");
                System.out.println("-------------------------");
                System.out.println("");
                System.out.println("Titulo: "+aux.getTitulo());
                System.out.println("Año: "+aux.getAño());
                System.out.println("Director: "+aux.getDirector());
                System.out.println("Duración: "+aux.getDuracion());
                System.out.println("Sinopsis: "+aux.getSinopsis());
                System.out.println("Reparto: "+aux.getReparto());
                System.out.println("Sesión: "+aux.getHora());
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CharBufferStreams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Fin de fichero");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CharBufferStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
