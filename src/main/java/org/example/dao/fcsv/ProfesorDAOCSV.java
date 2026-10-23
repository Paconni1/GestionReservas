package org.example.dao.fcsv;

import org.example.dao.ProfesorDAO;
import org.example.modelo.Departamento;
import org.example.modelo.Profesor;
import java.io.*;
import java.util.ArrayList;

public class ProfesorDAOCSV implements ProfesorDAO {
    private static final String RUTA = "data/csv/profesores.csv";
    private final DepartamentoDAOCSV departamentoDAO = new DepartamentoDAOCSV();

    @Override
    public ArrayList<Profesor> leerFichero() throws IOException {
        ArrayList<Profesor> lista = new ArrayList<>();
        File fichero = new File(RUTA);
        if (!fichero.exists()) return lista;

        ArrayList<Departamento> departamentos = departamentoDAO.leerFichero();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");


                if (p.length >= 5) {
                    String idProf = p[0].trim();

                    String nombreCompleto = p[1].trim() + " " + p[2].trim();
                    String email = p[3].trim();
                    String idDep = p[4].trim();

                    Departamento dep = departamentos.stream()
                            .filter(d -> d.getIdDepartamento().equalsIgnoreCase(idDep))
                            .findFirst()
                            .orElse(new Departamento(idDep, idDep));

                    lista.add(new Profesor(idProf, nombreCompleto, email, dep));
                }
            }
        }
        return lista;
    }

    @Override
    public void escribirFichero(ArrayList<Profesor> profesores) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            for (Profesor p : profesores) {
                String idDep = (p.getDepartamento() != null) ? p.getDepartamento().getIdDepartamento() : "";

                bw.write(p.getIdProfesor() + ";" + p.getNombre() + ";-;" + p.getEmail() + ";" + idDep);
                bw.newLine();
            }
        }
    }
}