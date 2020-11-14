package model;

import java.util.Set;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class AnhoLanzamiento implements Comparable<AnhoLanzamiento> {
    @XmlElement
    private int anho;
    @XmlElement
    private Set <Pelicula> peliculas;


    public int getAnhoLanzamiento() {
        return this.anho;
    }

    public Set<Pelicula> getPeliculasLista() {
        return this.peliculas;
    }

    public int compareTo(AnhoLanzamiento p){
        return (this.anho==p.anho)?0:(this.anho>p.anho)?1:-1;
    }

    @Override
    public String toString() {
        String s= "{" +
            " anho='" + this.anho + '\'' + "'" +
            ", peliculas='";
            for (Pelicula p : this.peliculas) {
                s+=" " + p.toString() + '\n';
            }
            s += "'" +
            "}";
        return s;
    }

    public AnhoLanzamiento() {
    }
    
    public AnhoLanzamiento(int anho) {
        this.anho = anho;
        this.peliculas = new TreeSet<>();
    }

}
