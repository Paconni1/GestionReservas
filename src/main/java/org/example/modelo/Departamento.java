package org.example.modelo;

import java.io.Serializable;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idDepartamento;
    private String nombre;

    public Departamento() {}

    public Departamento(String idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }


    public Departamento(int idDepartamento, String nombre) {
        this(String.valueOf(idDepartamento), nombre);
    }

    public String getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(String idDepartamento) { this.idDepartamento = idDepartamento; }


    public String getId() { return idDepartamento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(idDepartamento, that.idDepartamento);
    }

    @Override
    public int hashCode() { return Objects.hash(idDepartamento); }

    @Override
    public String toString() { return idDepartamento + " - " + nombre; }
}