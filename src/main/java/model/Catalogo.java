package model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Catalogo {

    @XmlElement
    private Date fechaGenerado;

    @XmlElement(name="anho_lanzamiento")
    private Set<AnhoLanzamiento> anhoLanzamientos;
    
    @Override
    public String toString() {
        String str = "{" +
            " fechaGenerado='" + this.fechaGenerado + '\'' + "'" +
            ", anhoLanzamientos='" +'{';
            for(AnhoLanzamiento anhol:this.anhoLanzamientos)
                str+=anhol.toString()+'\n';
            str+= "'" +"}";
            return str;
    }

    

    public void addAnhoLanzamientos(AnhoLanzamiento lanzamiento) {
        this.anhoLanzamientos.add(lanzamiento);
    }

    public Catalogo() {
        this.fechaGenerado=null;
        this.anhoLanzamientos=new TreeSet<>();
    }

	public void setFechaGenerado(Date time) {
        this.fechaGenerado=time;
	}

	public Set<AnhoLanzamiento> getAnhoLanzamientos() {
		return this.anhoLanzamientos;
	}
}
