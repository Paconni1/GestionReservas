package org.example.dao.fcsv;

import org.example.dao.DepartamentoDAO;
import org.example.modelo.Departamento;
import java.io.*;
import java.util.ArrayList;

public class DepartamentoDAOCSV implements DepartamentoDAO {
    private static final String RUTA = "data/csv/departamento.csv";

    @Override
    public ArrayList<Departamento> leerFichero() throws IOException {
        ArrayList<Departamento> lista = new ArrayList<>();
        File fichero = new File(RUTA);
        if (!fichero.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(";");
                if (partes.length >= 2) {
                    lista.add(new Departamento(partes[0].trim(), partes[1].trim()));
                }
            }
        }
        return lista;
    }

    @Override
    public void escribirFichero(ArrayList<Departamento> departamentos) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            for (Departamento d : departamentos) {
                bw.write(d.getIdDepartamento() + ";" + d.getNombre());
                bw.newLine();
            }
        }
    }
}