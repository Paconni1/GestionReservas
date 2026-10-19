package org.example.dao;

import org.example.modelo.Departamento;
import java.io.IOException;
import java.util.ArrayList;

public interface DepartamentoDAO {
    ArrayList<Departamento> leerFichero() throws IOException;
    void escribirFichero(ArrayList<Departamento> departamentos) throws IOException;
}