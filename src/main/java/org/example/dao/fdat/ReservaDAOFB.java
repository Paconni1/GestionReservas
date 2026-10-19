package org.example.dao.fdat;

import org.example.dao.ReservaDAO;
import org.example.modelo.Reserva;
import java.io.*;
import java.util.ArrayList;

public class ReservaDAOFB implements ReservaDAO {
    private static final String RUTA = "data/dat/reserva.dat";

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Reserva> leerFichero() throws IOException {
        File fichero = new File(RUTA);
        if (!fichero.exists() || fichero.length() == 0) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (ArrayList<Reserva>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Error de clase al leer reserva.dat: " + e.getMessage(), e);
        }
    }

    @Override
    public void escribirFichero(ArrayList<Reserva> reservas) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(reservas);
        }
    }
}