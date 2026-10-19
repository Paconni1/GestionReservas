package org.example.dao.fcsv;

import org.example.dao.EspacioDAO;
import org.example.modelo.Espacio;
import java.io.*;
import java.util.ArrayList;

public class EspacioDAOCSV implements EspacioDAO {
    private static final String RUTA = "data/csv/espacios.csv";

    @Override
    public ArrayList<Espacio> leerFichero() throws IOException {
        ArrayList<Espacio> lista = new ArrayList<>();
        File fichero = new File(RUTA);
        if (!fichero.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                if (p.length >= 5) {
                    lista.add(new Espacio(
                            p[0].trim(),
                            p[1].trim(),
                            p[2].trim(),
                            Integer.parseInt(p[3].trim()),
                            Boolean.parseBoolean(p[4].trim())
                    ));
                }
            }
        }
        return lista;
    }

    @Override
    public void escribirFichero(ArrayList<Espacio> espacios) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            for (Espacio e : espacios) {
                bw.write(e.getIdEspacio() + ";" + e.getNombre() + ";" + e.getTipo() + ";" +
                        e.getCapacidad() + ";" + e.isDisponible());
                bw.newLine();
            }
        }
    }
}