package model.estructuras;

import java.util.ArrayList;
import java.util.List;
import model.ordenamiento.Heapsort;

public class TablaHash {
    private static final int TAMANO_TABLA = 10;
    private final List<EntradaTabla>[] tabla;

    public TablaHash() {
        tabla = new ArrayList[TAMANO_TABLA];
        for (int i = 0; i < TAMANO_TABLA; i++) {
            tabla[i] = new ArrayList<>();
        }
    }

    public void insertar(String clave, String valor) {
        int indice = funcionDeHash(clave);
        tabla[indice].add(new EntradaTabla(clave, valor));
    }

    public String buscar(String clave) {
        int indice = funcionDeHash(clave);
        for (EntradaTabla entrada : tabla[indice]) {
            if (entrada.getClave().equals(clave)) {
                return entrada.getValor();
            }
        }
        return null; // Clave no encontrada
    }

    private int funcionDeHash(String clave) {
        return Math.abs(clave.hashCode() % TAMANO_TABLA);
    }

    public void ordenar() {
        // Recopilar todos los valores de la tabla
        List<String> valores = new ArrayList<>();
        for (List<EntradaTabla> entradas : tabla) {
            for (EntradaTabla entrada : entradas) {
                valores.add(entrada.getValor());
            }
        }

        // Convertir la lista de valores a un array para usar HeapSort
        String[] valoresArray = valores.toArray(String[]::new);

        // Aplicar HeapSort
        Heapsort.sort(valoresArray);

        // Actualizar los valores ordenados en la tabla
        int indice = 0;
        for (List<EntradaTabla> entradas : tabla) {
            entradas.clear(); // Limpiar la lista actual
            for (String valor : valoresArray) {
                if (buscarPorValor(valor) != null) {
                    entradas.add(new EntradaTabla(buscarPorValor(valor).getClave(), valor));
                }
            }
        }
    }

    public List<EntradaTabla>[] getTabla() {
        return tabla;
    }

    private EntradaTabla buscarPorValor(String valor) {
        for (List<EntradaTabla> entradas : tabla) {
            for (EntradaTabla entrada : entradas) {
                if (entrada.getValor().equals(valor)) {
                    return entrada;
                }
            }
        }
        return null;
    }
}
