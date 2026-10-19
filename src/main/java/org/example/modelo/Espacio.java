package org.example.modelo;

import java.io.Serializable;

public class Espacio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idEspacio;
    private String nombre;
    private String tipo;
    private int capacidad;
    private boolean disponible;

    public Espacio() {}

    public Espacio(String idEspacio, String nombre, String tipo, int capacidad, boolean disponible) {
        this.idEspacio = idEspacio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }


    public Espacio(int idEspacio, String nombre, String tipo, int capacidad, boolean disponible) {
        this(String.valueOf(idEspacio), nombre, tipo, capacidad, disponible);
    }

    public String getIdEspacio() { return idEspacio; }
    public void setIdEspacio(String idEspacio) { this.idEspacio = idEspacio; }

    public String getId() { return idEspacio; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() { return idEspacio + " - " + nombre + " (Cap: " + capacidad + ")"; }
}