package org.example.dao.fdat;

import org.example.dao.DepartamentoDAO;
import org.example.modelo.Departamento;
import java.io.*;
import java.util.ArrayList;

public class DepartamentoDAOFB implements DepartamentoDAO {
    private static final String RUTA = "data/dat/departamento.dat";

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Departamento> leerFichero() throws IOException {
        File fichero = new File(RUTA);
        if (!fichero.exists() || fichero.length() == 0) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (ArrayList<Departamento>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Error de clase al leer departamento.dat: " + e.getMessage(), e);
        }
    }

    @Override
    public void escribirFichero(ArrayList<Departamento> departamentos) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(departamentos);
        }
    }
}