
package model;
import java.util.ArrayList;

public class Prestamo {
    private Usuario usuario;
    private ArrayList<Libro> libros = new ArrayList<Libro>();

    //constructor
    public Prestamo(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    //getters y setter

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    
    
    
}
