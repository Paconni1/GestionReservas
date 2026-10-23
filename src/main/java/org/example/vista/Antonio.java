package org.example.vista;

import javafx.stage.Stage;
import org.example.dao.fjson.*;
import org.example.servicio.CentroEducativo;
import java.io.IOException;

public class Antonio extends MainFX {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            // Inyecta los DAO de Gson
            this.centro = new CentroEducativo(new DepartamentoDAOFJS(), new ProfesorDAOFJS(), new EspacioDAOFJS(), new ReservaDAOFJS());

            super.start(stage);
            stage.setTitle("Gestión de reservas - Alumno 3 (JSON)");
        } catch (IOException e) {
            mostrarError("Error crítico", "No se pudieron cargar los ficheros JSON: " + e.getMessage());
        }
    }
}