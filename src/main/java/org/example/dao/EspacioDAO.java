package org.example.dao;

import org.example.modelo.Espacio;
import java.io.IOException;
import java.util.ArrayList;

public interface EspacioDAO {
    ArrayList<Espacio> leerFichero() throws IOException;
    void escribirFichero(ArrayList<Espacio> espacios) throws IOException;
}