package org.iesalandalus.programacion.javafx.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/estudio_foto";
    private static final String USER = "root";
    private static final String PASS = "774411";

    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}