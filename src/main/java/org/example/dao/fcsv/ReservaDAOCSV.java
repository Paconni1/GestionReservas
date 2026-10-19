package org.example.dao.fcsv;

import org.example.dao.ReservaDAO;
import org.example.modelo.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.example.dao.ReservaDAO;
import org.example.modelo.*;
import java.io.*;

public class ReservaDAOCSV implements ReservaDAO {
    private static final String RUTA = "data/csv/reserva.csv";
    private final ProfesorDAOCSV profesorDAO = new ProfesorDAOCSV();
    private final EspacioDAOCSV espacioDAO = new EspacioDAOCSV();

    @Override
    public ArrayList<Reserva> leerFichero() throws IOException {
        ArrayList<Reserva> lista = new ArrayList<>();
        File fichero = new File(RUTA);
        if (!fichero.exists()) return lista;

        ArrayList<Profesor> profesores = profesorDAO.leerFichero();
        ArrayList<Espacio> espacios = espacioDAO.leerFichero();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                if (p.length >= 8) {
                    String idReserva = p[0].trim();
                    LocalDate fecha = LocalDate.parse(p[1].trim());
                    LocalTime horaInicio = LocalTime.parse(p[2].trim());
                    LocalTime horaFin = LocalTime.parse(p[3].trim());
                    String idProf = p[4].trim();
                    String idEsp = p[5].trim();
                    int asistentes = Integer.parseInt(p[6].trim());
                    EstadoReserva estado = EstadoReserva.valueOf(p[7].trim().toUpperCase());

                    Profesor prof = profesores.stream()
                            .filter(pr -> pr.getIdProfesor().equals(idProf))
                            .findFirst()
                            .orElse(new Profesor(idProf, idProf, "", null));

                    Espacio esp = espacios.stream()
                            .filter(es -> es.getIdEspacio().equals(idEsp))
                            .findFirst()
                            .orElse(new Espacio(idEsp, idEsp, "", 0, true));

                    lista.add(new Reserva(idReserva, fecha, horaInicio, horaFin, prof, esp, asistentes, estado));
                }
            }
        }
        return lista;
    }

    @Override
    public void escribirFichero(ArrayList<Reserva> reservas) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            for (Reserva r : reservas) {
                String idProf = (r.getProfesor() != null) ? r.getProfesor().getIdProfesor() : "";
                String idEsp = (r.getEspacio() != null) ? r.getEspacio().getIdEspacio() : "";
                bw.write(r.getIdReserva() + ";" + r.getFecha() + ";" + r.getHoraInicio() + ";" +
                        r.getHoraFin() + ";" + idProf + ";" + idEsp + ";" +
                        r.getNumAsistentes() + ";" + r.getEstado().name());
                bw.newLine();
            }
        }
    }
}
