package org.example.dao.fdat;

import org.example.dao.EspacioDAO;
import org.example.modelo.Espacio;
import java.io.*;
import java.util.ArrayList;

public class EspacioDAOFB implements EspacioDAO {
    private static final String RUTA = "data/dat/espacios.dat";

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Espacio> leerFichero() throws IOException {
        File fichero = new File(RUTA);
        if (!fichero.exists() || fichero.length() == 0) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (ArrayList<Espacio>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Error de clase al leer espacios.dat: " + e.getMessage(), e);
        }
    }

    @Override
    public void escribirFichero(ArrayList<Espacio> espacios) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(espacios);
        }
    }
}
