package org.iesalandalus.programacion.javafx.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.iesalandalus.programacion.javafx.modelo.Cliente;
import org.iesalandalus.programacion.javafx.modelo.dao.ClienteDAO;
import org.iesalandalus.programacion.javafx.utilidades.Controlador;
import org.iesalandalus.programacion.javafx.utilidades.Controladores;

public class ClienteControlador extends Controlador {

    @FXML private FlowPane fpClientes;
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    public void initialize() {
        cargarClientes();
    }

    private void cargarClientes() {
        fpClientes.getChildren().clear();
        for (Cliente c : clienteDAO.buscarTodos()) {
            fpClientes.getChildren().add(crearTarjeta(c));
        }
    }

    private VBox crearTarjeta(Cliente c) {
        VBox card = new VBox(10);
        card.getStyleClass().add("cliente-card");
        Label nombre = new Label(c.getNombre());
        nombre.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 14px;");
        Label email = new Label(c.getEmail());
        email.setStyle("-fx-text-fill: #00F5FF;");

        Button btnEditar = new Button("Editar");
        btnEditar.setOnAction(e -> abrirFormulario("Editar Cliente", c));
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> {
            clienteDAO.eliminar(c);
            cargarClientes();
        });

        card.getChildren().addAll(nombre, email, btnEditar, btnEliminar);
        return card;
    }

    @FXML
    private void nuevoCliente() {
        abrirFormulario("Nuevo Cliente", null);
    }

    private void abrirFormulario(String titulo, Cliente cliente) {
        // La ruta DEBE empezar por "/" para buscar desde la raíz del classpath/resources
        Controlador ctrl = Controladores.get("/vistas/FormularioCliente.fxml", titulo, getEscenario());

        if (ctrl instanceof FormularioClienteControlador) {
            FormularioClienteControlador formulario = (FormularioClienteControlador) ctrl;
            if (cliente != null) {
                formulario.setCliente(cliente);
            }
            formulario.getEscenario().showAndWait();
            cargarClientes();
        }
    }
}