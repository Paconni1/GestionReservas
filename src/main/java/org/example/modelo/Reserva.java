package org.example.modelo;

import java.util.Date;

public class Reserva {
    private int id;
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private String profesor;
    private String espacio;
    private int numeroAsistentes;
    private String estado;

    public Reserva(int id, Date fecha, String horaInicio, String horaFin, String profesor, String espacio, int numeroAsistentes, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.profesor = profesor;
        this.espacio = espacio;
        this.numeroAsistentes = numeroAsistentes;
        this.estado = estado;
    }

    public Reserva() {
    }



    //getter y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public int getNumeroAsistentes() {
        return numeroAsistentes;
    }

    public void setNumeroAsistentes(int numeroAsistentes) {
        this.numeroAsistentes = numeroAsistentes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
