package org.example.modelo;

import java.io.Serializable;

public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idProfesor;
    private String nombre;
    private String email;
    private Departamento departamento;

    public Profesor() {}

    public Profesor(String idProfesor, String nombre, String email, Departamento departamento) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
    }


    public Profesor(int idProfesor, String nombre, String email, Departamento departamento) {
        this(String.valueOf(idProfesor), nombre, email, departamento);
    }

    public String getIdProfesor() { return idProfesor; }
    public void setIdProfesor(String idProfesor) { this.idProfesor = idProfesor; }

    public String getId() { return idProfesor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    @Override
    public String toString() { return idProfesor + " - " + nombre; }
}