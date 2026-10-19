package org.example.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idReserva;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Profesor profesor;
    private Espacio espacio;
    private int numAsistentes;
    private EstadoReserva estado;

    public Reserva() {}

    public Reserva(String idReserva, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                   Profesor profesor, Espacio espacio, int numAsistentes, EstadoReserva estado) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.profesor = profesor;
        this.espacio = espacio;
        this.numAsistentes = numAsistentes;
        this.estado = estado;
    }


    public Reserva(int idReserva, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                   Profesor profesor, Espacio espacio, int numAsistentes, EstadoReserva estado) {
        this(String.valueOf(idReserva), fecha, horaInicio, horaFin, profesor, espacio, numAsistentes, estado);
    }

    public String getIdReserva() { return idReserva; }
    public void setIdReserva(String idReserva) { this.idReserva = idReserva; }

    public String getId() { return idReserva; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    public Espacio getEspacio() { return espacio; }
    public void setEspacio(Espacio espacio) { this.espacio = espacio; }

    public int getNumAsistentes() { return numAsistentes; }
    public void setNumAsistentes(int numAsistentes) { this.numAsistentes = numAsistentes; }

    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }
}
