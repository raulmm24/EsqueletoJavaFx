package org.iesalandalus.programacion.javafx.controladores;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.iesalandalus.programacion.javafx.utilidades.Controlador;
import org.iesalandalus.programacion.javafx.utilidades.Controladores;

import java.util.Optional;

public class MenuControlador extends Controlador {

    @FXML private AnchorPane sidebar;
    private boolean menuAbierto = false;

    @FXML
    public void initialize() {
        // Inicialización: el menú empieza oculto y no ocupa espacio en el layout
        sidebar.setVisible(false);
        sidebar.setManaged(false);
    }

    @FXML
    private void toggleMenu() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidebar);

        if (!menuAbierto) {
            // Abrir menú
            sidebar.setManaged(true); // Ocupa espacio
            sidebar.setVisible(true);
            transition.setToX(0);
            menuAbierto = true;
        } else {
            // Cerrar menú
            transition.setToX(-200); // Coincide con el ancho de 200px
            transition.setOnFinished(e -> {
                sidebar.setVisible(false);
                sidebar.setManaged(false); // Deja de ocupar espacio para centrar el contenido
            });
            menuAbierto = false;
        }
        transition.play();
    }

    @FXML
    private void abrirClientes() {
        Controlador ventana = Controladores.get("/vistas/Cliente.fxml", "Gestión de Clientes", getEscenario());
        if (ventana != null) {
            ventana.getEscenario().show();
        }
    }

    @FXML
    private void abrirSesiones() {
        // Implementación futura
    }

    @FXML
    private void salir() {
        // Creamos el diálogo de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar salida");
        alert.setHeaderText("¿Estás seguro de que quieres salir?");
        alert.setContentText("Se cerrará la aplicación.");

        // Aplicamos el estilo de la app al diálogo (opcional, requiere que el CSS esté cargado)
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/estilos/menu.css").toExternalForm());

        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(botonSi, botonNo);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == botonSi) {
            if (getEscenario() != null) {
                getEscenario().close();
            }
        }
    }
}