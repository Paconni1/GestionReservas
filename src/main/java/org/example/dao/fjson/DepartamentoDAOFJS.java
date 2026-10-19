package org.example.dao.fjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.dao.DepartamentoDAO;
import org.example.modelo.Departamento;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DepartamentoDAOFJS implements DepartamentoDAO {
    private static final String RUTA = "data/json/departamento.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public ArrayList<Departamento> leerFichero() throws IOException {
        File fichero = new File(RUTA);
        if (!fichero.exists() || fichero.length() == 0) return new ArrayList<>();

        try (FileReader reader = new FileReader(fichero)) {
            Type tipoLista = new TypeToken<ArrayList<Departamento>>(){}.getType();
            ArrayList<Departamento> lista = gson.fromJson(reader, tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        }
    }

    @Override
    public void escribirFichero(ArrayList<Departamento> departamentos) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(fichero)) {
            gson.toJson(departamentos, writer);
        }
    }
}