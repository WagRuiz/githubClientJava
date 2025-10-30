package com.una.githubclient.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aplicación principal JavaFX para el cliente de GitHub.
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar la escena principal desde el archivo FXML ubicado en resources/com/una/githubclient/ui/primary.fxml
        Parent root = loadFXML("primary");

        scene = new Scene(root, 980, 600); // Tamaño inicial recomendado
        stage.setTitle("GitHub Client — Proyecto UNA"); // Título de la ventana
        stage.setScene(scene);

        // Configuración visual de la ventana
        stage.setMinWidth(860);
        stage.setMinHeight(540);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Permite cambiar la raíz de la escena actual, útil para cambiar de vistas.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga un archivo FXML desde el paquete /resources/com/una/githubclient/ui/
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    /**
     * Punto de entrada de la aplicación JavaFX.
     */
    public static void main(String[] args) {
        launch();
    }
}
