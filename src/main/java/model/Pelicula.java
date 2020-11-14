package model;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pelicula implements Comparable<Pelicula> {
    @XmlElement
    private String titulo;
    @XmlElement
    private String generos;
    @XmlElement
    private Date fechaLanzamiento;
    @XmlElement
    private String clasificacion;
    @XmlElement
    private String duracion;
    @XmlElement
    private String argumento;
    @XmlElement
    private String reparto;

    @Override
    public String toString() {
        return "{" + " titulo='" + this.titulo + "'" + ", generos='" + this.generos + "'" + ", fechaLanzamiento='"
                + this.fechaLanzamiento + ", clasificacion='" + this.clasificacion + "'" + ", duracion='"
                + this.duracion + "'" + "'" + ", argumento='" + this.argumento + "'" + "'" + ", reparto='"
                + this.reparto + "'" + "'" + ", idioma='" + this.idioma + "'" + "'" + ", paisLanzamiento='"
                + this.paisLanzamiento + "'" + "}";
    }

    @XmlElement
    private String idioma;
    @XmlElement
    private String paisLanzamiento;

    public Date getFechaLanzamiento() {
        return this.fechaLanzamiento;
    }

    public int compareTo(Pelicula p) {
        return (this.fechaLanzamiento.before(p.getFechaLanzamiento())) ? -1
                : (this.fechaLanzamiento.after(p.getFechaLanzamiento())) ? 1 : 0;
    }

    public Pelicula(String titulo, String generos, Date fechaLanzamiento, String clasificacion, String duracion,
            String argumento, String reparto, String idioma, String paisLanzamiento) {
        this.titulo = titulo;
        this.generos = generos;
        this.fechaLanzamiento = fechaLanzamiento;
        this.clasificacion = clasificacion;
        this.duracion = duracion;
        this.argumento = argumento;
        this.reparto = reparto;
        this.idioma = idioma;
        this.paisLanzamiento = paisLanzamiento;
    }

    public Pelicula() {
    }

}
