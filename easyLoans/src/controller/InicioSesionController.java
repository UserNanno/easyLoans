package controller;

import java.util.List;
import model.estructuras.ListaEnlazada;
import model.estructuras.NodoListaEnlazada;
import model.ordenamiento.Mergesort;
import model.busqueda.BusquedaBinaria;
import model.AdministradorDAO;
import persistencia.ConexionMysql;

public class InicioSesionController {

    private final AdministradorDAO adminDAO;
    private final ListaEnlazada listaUsuarios;

    public InicioSesionController() {
        this.adminDAO = new AdministradorDAO(new ConexionMysql());
        this.listaUsuarios = new ListaEnlazada();
        cargarUsuariosDesdeBD();
    }

    private void cargarUsuariosDesdeBD() {
        List<String> usuariosDesdeBD = adminDAO.obtenerUsuariosDesdeBD();

        for (String usuario : usuariosDesdeBD) {
            listaUsuarios.insertar(usuario);
        }
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        // Utiliza el algoritmo de ordenamiento Mergesort para ordenar la lista de usuarios
        String[] arrayUsuarios = convertirListaAArray();
        Mergesort.mergesort(arrayUsuarios);

        System.out.println("La lista de usuarios ha sido ordenada con Mergesort.");

        // Utiliza el algoritmo de búsqueda binaria para buscar el usuario
        int indiceUsuario = BusquedaBinaria.busquedaBinaria(arrayUsuarios, nombreUsuario);

        if (indiceUsuario != -1) {
            // Usuario encontrado en la lista ordenada
            System.out.println("Se encontro el usuario en la posicion " + indiceUsuario + " utilizando BusquedaBinaria.");

            if (adminDAO.verificarCredenciales(nombreUsuario, contraseña)) {
                System.out.println("Inicio de sesiOn exitoso.");
                return true;
            } else {
                System.out.println("Credenciales invalidas.");
            }
        } else {
            // Usuario no encontrado en la lista
            System.out.println("Usuario no encontrado en la lista.");
        }

        return false;
    }

    private String[] convertirListaAArray() {
        // Convierte los elementos de la lista en un array
        String[] arrayUsuarios = new String[listaUsuarios.size()];
        NodoListaEnlazada actual = listaUsuarios.getCabeza();
        int indice = 0;
        while (actual != null) {
            arrayUsuarios[indice++] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return arrayUsuarios;
    }
}
