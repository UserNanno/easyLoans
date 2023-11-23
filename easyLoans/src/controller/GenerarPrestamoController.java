package controller;

import model.PrestamoDAO;

import java.util.Date;

public class GenerarPrestamoController {
    private final PrestamoDAO prestamoDAO;

    public GenerarPrestamoController() {
        this.prestamoDAO = new PrestamoDAO();
    }

    public boolean generarPrestamo(int idLibro, int idUsuario, Date fechaDevolucion) {

        // Llamada al método de generación de préstamo en el DAO
        return prestamoDAO.generarPrestamo(idLibro, idUsuario, fechaDevolucion);
    }
}
