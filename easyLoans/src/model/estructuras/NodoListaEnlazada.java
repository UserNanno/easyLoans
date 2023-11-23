package model.estructuras;

public class NodoListaEnlazada {

    private String dato;
    private NodoListaEnlazada siguiente;

    public NodoListaEnlazada(String dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public String getDato() {
        return dato;
    }

    public NodoListaEnlazada getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaEnlazada siguiente) {
        this.siguiente = siguiente;
    }
}
