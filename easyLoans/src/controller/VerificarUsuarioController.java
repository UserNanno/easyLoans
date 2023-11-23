package controller;

import model.UsuarioDAO;
import model.estructuras.EntradaTabla;
import model.estructuras.TablaHash;
import model.ordenamiento.Heapsort;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VerificarUsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final TablaHash tablaHash;
    private final DefaultTableModel tblModel;

    public VerificarUsuarioController(DefaultTableModel tblModel) {
        this.usuarioDAO = new UsuarioDAO();
        this.tablaHash = new TablaHash();
        this.tblModel = tblModel;
    }

    public boolean verificarUsuario(String dni) {
        boolean usuarioExistente = usuarioDAO.verificarUsuario(dni);

        if (usuarioExistente) {
            agregarUsuarioATablaHash(dni);
            System.out.println("Usuario encontrado en la tabla hash.");
        } else {
            System.out.println("Usuario no encontrado en la tabla hash.");
        }

        return usuarioExistente;
    }

    public void ordenarTabla() {
        List<String> valores = recopilarValoresDeTabla();
        String[] valoresArray = valores.toArray(String[]::new);
        aplicarHeapsort(valoresArray);
        actualizarTablaConValoresOrdenados(valoresArray);
    }

    private void agregarUsuarioATablaHash(String dni) {
        String[] informacionUsuario = usuarioDAO.obtenerInformacionUsuario(dni);
        tablaHash.insertar(informacionUsuario[0], informacionUsuario[1]);
        actualizarTabla();
    }

    private List<String> recopilarValoresDeTabla() {
        List<String> valores = new ArrayList<>();
        for (List<EntradaTabla> entradas : tablaHash.getTabla()) {
            for (EntradaTabla entrada : entradas) {
                valores.add(entrada.getValor());
            }
        }
        return valores;
    }

    private void aplicarHeapsort(String[] valoresArray) {
        Heapsort.sort(valoresArray);
    }

    private void actualizarTablaConValoresOrdenados(String[] valoresArray) {
        int indice = 0;
        for (List<EntradaTabla> entradas : tablaHash.getTabla()) {
            entradas.clear(); // Limpiar la lista actual
            for (String valor : valoresArray) {
                if (buscarPorValor(valor) != null) {
                    entradas.add(new EntradaTabla(buscarPorValor(valor).getClave(), valor));
                }
            }
        }
    }

    private void actualizarTabla() {
        tblModel.setRowCount(0);

        // Recopilar todos los valores de la tabla y agregarlos al modelo
        for (List<EntradaTabla> entradas : tablaHash.getTabla()) {
            for (EntradaTabla entrada : entradas) {
                String dni = entrada.getClave();
                String[] informacionUsuario = usuarioDAO.obtenerInformacionUsuario(dni);

                if (informacionUsuario != null) {
                    tblModel.addRow(new Object[]{dni, informacionUsuario[2], informacionUsuario[1]});
                }
            }
        }
    }

    private EntradaTabla buscarPorValor(String valor) {
        for (List<EntradaTabla> entradas : tablaHash.getTabla()) {
            for (EntradaTabla entrada : entradas) {
                if (entrada.getValor().equals(valor)) {
                    return entrada;
                }
            }
        }
        return null;
    }

}
