package org.iesalandalus.programacion.javafx.modelo.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iesalandalus.programacion.javafx.modelo.Cliente;
import java.sql.*;

public class ClienteDAO {

    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Nuevo método esencial para el CRUD
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, telefono = ? WHERE id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getId()); // Asumiendo que el modelo tiene el ID
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Sobrecarga para que sea más fácil llamar desde el controlador
    public void eliminar(Cliente cliente) {
        eliminar(cliente.getId());
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public ObservableList<Cliente> buscarTodos() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = Conexion.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("email"), rs.getString("telefono"));
                clientes.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return clientes;
    }
}