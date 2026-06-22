package org.iesalandalus.programacion.javafx.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.javafx.modelo.Cliente;
import org.iesalandalus.programacion.javafx.modelo.dao.ClienteDAO;
import org.iesalandalus.programacion.javafx.utilidades.Controlador;

public class FormularioClienteControlador extends Controlador {

    @FXML private TextField tfNombre;
    @FXML private TextField tfEmail;
    @FXML private TextField tfTelefono;

    private Cliente clienteAEditar;
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void setCliente(Cliente cliente) {
        this.clienteAEditar = cliente;
        tfNombre.setText(cliente.getNombre());
        tfEmail.setText(cliente.getEmail());
        tfTelefono.setText(cliente.getTelefono());
    }

    @FXML
    private void cerrar() {
        ((Stage) tfNombre.getScene().getWindow()).close();
    }

    @FXML
    private void guardar() {
        if (clienteAEditar == null) {
            clienteDAO.insertar(new Cliente(tfNombre.getText(), tfEmail.getText(), tfTelefono.getText()));
        } else {
            clienteAEditar.setNombre(tfNombre.getText());
            clienteAEditar.setEmail(tfEmail.getText());
            clienteAEditar.setTelefono(tfTelefono.getText());
            clienteDAO.actualizar(clienteAEditar);
        }
        cerrar(); // Cierre seguro
    }
}