package org.example.dao;

import org.example.modelo.Reserva;
import java.io.IOException;
import java.util.ArrayList;

public interface ReservaDAO {
    ArrayList<Reserva> leerFichero() throws IOException;
    void escribirFichero(ArrayList<Reserva> reservas) throws IOException;
}