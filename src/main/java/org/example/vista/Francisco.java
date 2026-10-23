package org.example.vista;

import javafx.stage.Stage;
import org.example.dao.fcsv.*;
import org.example.servicio.CentroEducativo;
import java.io.IOException;

public class Francisco extends MainFX {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {

            this.centro = new CentroEducativo(new DepartamentoDAOCSV(), new ProfesorDAOCSV(), new EspacioDAOCSV(), new ReservaDAOCSV());


            super.start(stage);
            stage.setTitle("Gestión de reservas - Alumno 1 (CSV)");
        } catch (IOException e) {
            mostrarError("Error crítico", "No se pudieron cargar los ficheros CSV: " + e.getMessage());
        }
    }
}