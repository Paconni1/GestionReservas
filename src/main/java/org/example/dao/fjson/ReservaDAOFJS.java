package org.example.dao.fjson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.example.dao.ReservaDAO;
import org.example.modelo.Reserva;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReservaDAOFJS implements ReservaDAO {
    private static final String RUTA = "data/json/reserva.json";
    private final Gson gson;

    public ReservaDAOFJS() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, type, ctx) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, ctx) -> LocalDate.parse(json.getAsString()))
                .registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (src, type, ctx) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, ctx) -> LocalTime.parse(json.getAsString()))
                .setPrettyPrinting()
                .create();
    }

    @Override
    public ArrayList<Reserva> leerFichero() throws IOException {
        File fichero = new File(RUTA);
        if (!fichero.exists() || fichero.length() == 0) return new ArrayList<>();

        try (FileReader reader = new FileReader(fichero)) {
            Type tipoLista = new TypeToken<ArrayList<Reserva>>(){}.getType();
            ArrayList<Reserva> lista = gson.fromJson(reader, tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        }
    }

    @Override
    public void escribirFichero(ArrayList<Reserva> reservas) throws IOException {
        File fichero = new File(RUTA);
        if (fichero.getParentFile() != null) fichero.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(fichero)) {
            gson.toJson(reservas, writer);
        }
    }
}