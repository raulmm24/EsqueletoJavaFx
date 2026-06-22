package org.iesalandalus.programacion.javafx.modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private String telefono;

    // CONSTRUCTOR 1: Para crear un cliente nuevo desde la interfaz (sin ID)
    public Cliente(String nombre, String email, String telefono) {
        setNombre(nombre);
        setEmail(email);
        setTelefono(telefono);
    }

    // CONSTRUCTOR 2: Para cargar un cliente existente desde la BD (con ID)
    public Cliente(int id, String nombre, String email, String telefono) {
        this(nombre, email, telefono);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}