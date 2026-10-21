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
                // Ignorar líneas en blanco
                if (linea.trim().isEmpty()) continue;

                String[] p = linea.split(";");

                // El CSV de la profesora tiene 7 columnas, comprobamos que haya al menos 7
                if (p.length >= 7) {
                    String id = p[0].trim();
                    String nombre = p[1].trim();
                    String tipo = p[2].trim();
                    // p[3] es el edificio, nuestro modelo mínimo no lo requiere, lo omitimos al cargar el objeto
                    int capacidad = Integer.parseInt(p[4].trim()); // Ahora sí lee el número correctamente
                    // p[5] es el equipamiento, lo omitimos también

                    // Convertir el texto "DISPONIBLE" a true, y cualquier otra cosa a false
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
                // Convertimos el booleano de nuevo al texto que espera la profesora
                String estado = e.isDisponible() ? "DISPONIBLE" : "NO_DISPONIBLE";

                // Guardamos respetando la estructura de 7 columnas (ponemos "-" en los campos extra para no descuadrar el CSV)
                bw.write(e.getIdEspacio() + ";" + e.getNombre() + ";" + e.getTipo() + ";-;" +
                        e.getCapacidad() + ";-;" + estado);
                bw.newLine();
            }
        }
    }
}