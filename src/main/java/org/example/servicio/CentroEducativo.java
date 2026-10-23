package org.example.servicio;

import org.example.dao.*;
import org.example.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CentroEducativo {

    private final DepartamentoDAO departamentoDAO;
    private final ProfesorDAO profesorDAO;
    private final EspacioDAO espacioDAO;
    private final ReservaDAO reservaDAO;

    private ArrayList<Departamento> departamentos;
    private ArrayList<Profesor> profesores;
    private ArrayList<Espacio> espacios;
    private ArrayList<Reserva> reservas;

    public CentroEducativo(DepartamentoDAO depDAO, ProfesorDAO profDAO, EspacioDAO espDAO, ReservaDAO resDAO) throws IOException {
        this.departamentoDAO = depDAO;
        this.profesorDAO = profDAO;
        this.espacioDAO = espDAO;
        this.reservaDAO = resDAO;

        this.departamentos = departamentoDAO.leerFichero();
        this.profesores = profesorDAO.leerFichero();
        this.espacios = espacioDAO.leerFichero();
        this.reservas = reservaDAO.leerFichero();
    }

    public void guardarTodo() throws IOException {
        departamentoDAO.escribirFichero(departamentos);
        profesorDAO.escribirFichero(profesores);
        espacioDAO.escribirFichero(espacios);
        reservaDAO.escribirFichero(reservas);
    }

    // =========================================================
    // 6.1. GESTIÓN DE DEPARTAMENTOS
    // =========================================================

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void altaDepartamento(Departamento d) throws IOException {
        if (d.getIdDepartamento() == null || d.getIdDepartamento().isEmpty()) {
            throw new IllegalArgumentException("El ID del departamento es obligatorio.");
        }
        if (departamentos.stream().anyMatch(dep -> dep.getIdDepartamento().equalsIgnoreCase(d.getIdDepartamento()))) {
            throw new IllegalArgumentException("Ya existe un departamento con el ID: " + d.getIdDepartamento());
        }
        departamentos.add(d);
        departamentoDAO.escribirFichero(departamentos);
    }

    public void modificarDepartamento(Departamento modificado) throws IOException {
        Departamento existente = buscarDepartamento(modificado.getIdDepartamento());
        if (existente == null) {
            throw new IllegalArgumentException("No existe el departamento con ID: " + modificado.getIdDepartamento());
        }
        existente.setNombre(modificado.getNombre());
        departamentoDAO.escribirFichero(departamentos);
    }

    public void bajaDepartamento(String idDepartamento) throws IOException {
        boolean tieneProfesores = profesores.stream()
                .anyMatch(p -> p.getDepartamento() != null &&
                        p.getDepartamento().getIdDepartamento().equalsIgnoreCase(idDepartamento));
        if (tieneProfesores) {
            throw new IllegalStateException("No se puede eliminar un departamento que tenga profesores asociados.");
        }
        boolean eliminado = departamentos.removeIf(d -> d.getIdDepartamento().equalsIgnoreCase(idDepartamento));
        if (!eliminado) {
            throw new IllegalArgumentException("No existe el departamento con ID: " + idDepartamento);
        }
        departamentoDAO.escribirFichero(departamentos);
    }

    public void bajaDepartamento(int idDepartamento) throws IOException {
        bajaDepartamento(String.valueOf(idDepartamento));
    }

    public Departamento buscarDepartamento(String id) {
        return departamentos.stream()
                .filter(d -> d.getIdDepartamento().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    // =========================================================
    // 6.2. GESTIÓN DE PROFESORES
    // =========================================================

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void altaProfesor(Profesor p) throws IOException {
        if (p.getIdProfesor() == null || p.getIdProfesor().isEmpty()) {
            throw new IllegalArgumentException("El ID del profesor es obligatorio.");
        }
        if (profesores.stream().anyMatch(prof -> prof.getIdProfesor().equalsIgnoreCase(p.getIdProfesor()))) {
            throw new IllegalArgumentException("Ya existe un profesor con el ID: " + p.getIdProfesor());
        }
        if (p.getDepartamento() == null || buscarDepartamento(p.getDepartamento().getIdDepartamento()) == null) {
            throw new IllegalArgumentException("El profesor debe pertenecer a un departamento existente.");
        }
        profesores.add(p);
        profesorDAO.escribirFichero(profesores);
    }

    public void modificarProfesor(Profesor modificado) throws IOException {
        Profesor existente = buscarProfesor(modificado.getIdProfesor());
        if (existente == null) {
            throw new IllegalArgumentException("No existe el profesor con ID: " + modificado.getIdProfesor());
        }
        if (modificado.getDepartamento() == null || buscarDepartamento(modificado.getDepartamento().getIdDepartamento()) == null) {
            throw new IllegalArgumentException("El profesor debe pertenecer a un departamento existente.");
        }
        existente.setNombre(modificado.getNombre());
        existente.setEmail(modificado.getEmail());
        existente.setDepartamento(modificado.getDepartamento());
        profesorDAO.escribirFichero(profesores);
    }

    public void bajaProfesor(String idProfesor) throws IOException {
        boolean eliminado = profesores.removeIf(p -> p.getIdProfesor().equalsIgnoreCase(idProfesor));
        if (!eliminado) {
            throw new IllegalArgumentException("No existe el profesor con ID: " + idProfesor);
        }
        profesorDAO.escribirFichero(profesores);
    }

    public void bajaProfesor(int idProfesor) throws IOException {
        bajaProfesor(String.valueOf(idProfesor));
    }

    public List<Profesor> getProfesoresPorDepartamento(String idDepartamento) {
        return profesores.stream()
                .filter(p -> p.getDepartamento() != null &&
                        p.getDepartamento().getIdDepartamento().equalsIgnoreCase(idDepartamento))
                .collect(Collectors.toList());
    }

    public Profesor buscarProfesor(String id) {
        return profesores.stream()
                .filter(p -> p.getIdProfesor().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    // =========================================================
    // 6.3. GESTIÓN DE ESPACIOS
    // =========================================================

    public ArrayList<Espacio> getEspacios() {
        return espacios;
    }

    public void altaEspacio(Espacio e) throws IOException {
        if (e.getIdEspacio() == null || e.getIdEspacio().isEmpty()) {
            throw new IllegalArgumentException("El ID del espacio es obligatorio.");
        }
        if (espacios.stream().anyMatch(esp -> esp.getIdEspacio().equalsIgnoreCase(e.getIdEspacio()))) {
            throw new IllegalArgumentException("Ya existe un espacio con el ID: " + e.getIdEspacio());
        }
        if (e.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad del espacio debe ser mayor que 0.");
        }
        espacios.add(e);
        espacioDAO.escribirFichero(espacios);
    }

    public void modificarEspacio(Espacio modificado) throws IOException {
        Espacio existente = buscarEspacio(modificado.getIdEspacio());
        if (existente == null) {
            throw new IllegalArgumentException("No existe el espacio con ID: " + modificado.getIdEspacio());
        }
        if (modificado.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad del espacio debe ser mayor que 0.");
        }
        existente.setNombre(modificado.getNombre());
        existente.setTipo(modificado.getTipo());
        existente.setCapacidad(modificado.getCapacidad());
        existente.setDisponible(modificado.isDisponible());
        espacioDAO.escribirFichero(espacios);
    }

    public void modificarDisponibilidadEspacio(String idEspacio, boolean disponible) {
        Espacio existente = buscarEspacio(idEspacio);
        if (existente == null) {
            throw new IllegalArgumentException("No existe el espacio con ID: " + idEspacio);
        }
        existente.setDisponible(disponible);
        try {
            espacioDAO.escribirFichero(espacios);
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar disponibilidad: " + ex.getMessage(), ex);
        }
    }

    public void modificarDisponibilidadEspacio(int idEspacio, boolean disponible) {
        modificarDisponibilidadEspacio(String.valueOf(idEspacio), disponible);
    }

    public void bajaEspacio(String idEspacio) throws IOException {
        boolean eliminado = espacios.removeIf(e -> e.getIdEspacio().equalsIgnoreCase(idEspacio));
        if (!eliminado) {
            throw new IllegalArgumentException("No existe el espacio con ID: " + idEspacio);
        }
        espacioDAO.escribirFichero(espacios);
    }

    public Espacio buscarEspacio(String id) {
        return espacios.stream()
                .filter(e -> e.getIdEspacio().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    // =========================================================
    // 6.4. GESTIÓN DE RESERVAS Y REGLAS DE NEGOCIO
    // =========================================================

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void crearReserva(Reserva nueva) throws IOException {

        if (nueva.getIdReserva() == null || nueva.getIdReserva().isEmpty()) {
            throw new IllegalArgumentException("El ID de la reserva es obligatorio.");
        }
        if (reservas.stream().anyMatch(r -> r.getIdReserva().equalsIgnoreCase(nueva.getIdReserva()))) {
            throw new IllegalArgumentException("Ya existe una reserva con el ID: " + nueva.getIdReserva());
        }


        if (nueva.getProfesor() == null || buscarProfesor(nueva.getProfesor().getIdProfesor()) == null) {
            throw new IllegalArgumentException("Debe seleccionar un profesor válido.");
        }
        Espacio espacio = (nueva.getEspacio() != null) ? buscarEspacio(nueva.getEspacio().getIdEspacio()) : null;
        if (espacio == null) {
            throw new IllegalArgumentException("Debe seleccionar un espacio válido.");
        }


        if (!espacio.isDisponible()) {
            throw new IllegalStateException("No se puede reservar un espacio que no está disponible.");
        }


        if (nueva.getNumAsistentes() <= 0 || nueva.getNumAsistentes() > espacio.getCapacidad()) {
            throw new IllegalArgumentException("El número de asistentes (" + nueva.getNumAsistentes() +
                    ") supera la capacidad del espacio (" + espacio.getCapacidad() + ").");
        }


        if (nueva.getHoraInicio() == null || nueva.getHoraFin() == null || !nueva.getHoraFin().isAfter(nueva.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de finalización debe ser posterior a la hora de inicio.");
        }


        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.CANCELADA) continue;
            if (!r.getFecha().equals(nueva.getFecha())) continue;

            boolean solapa = nueva.getHoraInicio().isBefore(r.getHoraFin()) &&
                    r.getHoraInicio().isBefore(nueva.getHoraFin());

            if (solapa) {
                if (r.getEspacio().getIdEspacio().equalsIgnoreCase(espacio.getIdEspacio())) {
                    throw new IllegalStateException("El espacio ya tiene una reserva que se solapa en ese horario.");
                }
                if (r.getProfesor().getIdProfesor().equalsIgnoreCase(nueva.getProfesor().getIdProfesor())) {
                    throw new IllegalStateException("El profesor ya tiene otra reserva simultánea en ese horario.");
                }
            }
        }

        nueva.setEstado(EstadoReserva.CONFIRMADA);
        reservas.add(nueva);
        reservaDAO.escribirFichero(reservas);
    }

    public void cancelarReserva(String idReserva) throws IOException {
        Reserva r = reservas.stream()
                .filter(res -> res.getIdReserva().equalsIgnoreCase(idReserva))
                .findFirst()
                .orElse(null);
        if (r == null) {
            throw new IllegalArgumentException("No existe la reserva con ID: " + idReserva);
        }
        r.setEstado(EstadoReserva.CANCELADA);
        reservaDAO.escribirFichero(reservas);
    }

    public void cancelarReserva(int idReserva) throws IOException {
        cancelarReserva(String.valueOf(idReserva));
    }

    public List<Reserva> getReservasPorProfesor(String idProfesor) {
        return reservas.stream()
                .filter(r -> r.getProfesor() != null && r.getProfesor().getIdProfesor().equalsIgnoreCase(idProfesor))
                .collect(Collectors.toList());
    }

    public List<Reserva> getReservasPorEspacio(String idEspacio) {
        return reservas.stream()
                .filter(r -> r.getEspacio() != null && r.getEspacio().getIdEspacio().equalsIgnoreCase(idEspacio))
                .collect(Collectors.toList());
    }
}