/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicastream;

import java.io.Serializable;

/**
 *
 * @author juang
 */
public class Cartelera implements Serializable{
    private String titulo;
    private String año;
    private String director;
    private String duracion;
    private String sinopsis;
    private String reparto;
    private String hora;

    public Cartelera() {
    }
    
    public Cartelera(String titulo, String año, String director, String duracion, String sinopsis, String reparto, String hora) {
        this.titulo = titulo;
        this.año = año;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
    
}
