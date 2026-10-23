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


                if (p.length >= 7) {
                    String id = p[0].trim();
                    String nombre = p[1].trim();
                    String tipo = p[2].trim();

                    int capacidad = Integer.parseInt(p[4].trim());



                    boolean disponible = p[6].trim().equalsIgnoreCase("DISPONIBLE");

                    lista.add(new Espacio(id, nombre, tipo, capacidad, disponible));
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

                String estado = e.isDisponible() ? "DISPONIBLE" : "NO_DISPONIBLE";


                bw.write(e.getIdEspacio() + ";" + e.getNombre() + ";" + e.getTipo() + ";-;" +
                        e.getCapacidad() + ";-;" + estado);
                bw.newLine();
            }
        }
    }
}