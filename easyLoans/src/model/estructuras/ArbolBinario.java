package model.estructuras;

public class ArbolBinario {

    NodoArbolBinario raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarNodo(raiz, valor);
    }

    public NodoArbolBinario insertarNodo(NodoArbolBinario nodo, int valor) {
        if (nodo == null) {
            nodo = new NodoArbolBinario(valor);
            return nodo;
        }

        if (valor < nodo.valor) {
            nodo.izquierda = insertarNodo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertarNodo(nodo.derecha, valor);
        }

        return nodo;
    }

    public void imprimirOrden() {
        imprimirOrdenRec(raiz);
    }

    void imprimirOrdenRec(NodoArbolBinario nodo) {
        if (nodo != null) {
            imprimirOrdenRec(nodo.izquierda);
            System.out.println(nodo.valor); // Agregué espacio después del valor
            imprimirOrdenRec(nodo.derecha);
        }
    }

    public boolean contiene(int valor) {
        return contieneValor(raiz, valor);
    }

    boolean contieneValor(NodoArbolBinario nodo, int valor) {
        if (nodo == null) {
            return false;
        }

        if (valor == nodo.valor) {
            return true;
        }

        if (valor < nodo.valor) {
            return contieneValor(nodo.izquierda, valor);
        } else {
            return contieneValor(nodo.derecha, valor);
        }
    }
}
