package org.example.vista;

import javafx.stage.Stage;
import org.example.dao.fdat.*;
import org.example.servicio.CentroEducativo;
import java.io.IOException;

public class Javier extends MainFX {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            // Inyecta los DAO Binarios
            this.centro = new CentroEducativo(new DepartamentoDAOFB(), new ProfesorDAOFB(), new EspacioDAOFB(), new ReservaDAOFB());

            super.start(stage);
            stage.setTitle("Gestión de reservas - Alumno 2 (DAT)");
        } catch (IOException e) {
            mostrarError("Error crítico", "No se pudieron cargar los ficheros binarios: " + e.getMessage());
        }
    }
}