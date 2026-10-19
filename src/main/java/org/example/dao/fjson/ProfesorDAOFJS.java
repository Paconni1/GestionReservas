package org.example.dao.fjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.dao.ProfesorDAO;
import org.example.modelo.Profesor;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProfesorDAOFJS implements ProfesorDAO {
    private static final String RUTA = "data/json/profesores.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public ArrayList<Profesor> leerFichero() throws IOException {
        File fichero = new File(RUTA);
        if (!fichero.exists() || fichero.length() == 0) return new ArrayList<>();

        try (FileReader reader = new FileReader(fichero)) {
            Type tipoLista = new TypeToken<ArrayList<Profesor>>(){}.getType();
            ArrayList<Profesor> lista = gson.fromJson(reader, tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        }
    }

    @Override
    public void escribirFichero(ArrayList<Profesor> profesores) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(fichero)) {
            gson.toJson(profesores, writer);
        }
    }
}