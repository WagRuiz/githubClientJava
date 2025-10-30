package com.una.githubclient.ui;

import com.una.githubclient.domain.Repo;
import com.una.githubclient.domain.User;
import com.una.githubclient.domain.LanguageStats;
import com.una.githubclient.service.GitHubApi;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

/**
 * Controlador principal de la interfaz JavaFX.
 * Maneja los eventos definidos en primary.fxml.
 */
public class PrimaryController {

    // --- Elementos definidos en primary.fxml ---

    // Campo de entrada y botón
    @FXML private TextField txtUsername;
    @FXML private Button btnBuscar;

    // Datos del usuario
    @FXML private Label lblName;
    @FXML private Label lblBio;
    @FXML private Label lblFollowers;
    @FXML private Label lblFollowing;
    @FXML private ImageView imgAvatar;

    // Tabla de repositorios
    @FXML private TableView<Repo> tblRepos;
    @FXML private TableColumn<Repo, String> colName;
    @FXML private TableColumn<Repo, String> colLanguage;
    @FXML private TableColumn<Repo, Number> colStars;
    @FXML private TableColumn<Repo, String> colUpdated;

    // Gráfico de lenguajes
    @FXML private PieChart chartLanguages;

    // API service
    private final GitHubApi api = new GitHubApi();

    // Lista observable para la tabla
    private final ObservableList<Repo> repoData = FXCollections.observableArrayList();

    // ---------------------------------------------------------

    @FXML
    public void initialize() {
        // Configuración de columnas para el TableView
        colName.setCellValueFactory(cell -> cell.getValue().nameProperty());
        colLanguage.setCellValueFactory(cell -> cell.getValue().languageProperty());
        colStars.setCellValueFactory(cell -> cell.getValue().stargazersProperty());
        colUpdated.setCellValueFactory(cell -> cell.getValue().updatedAtPrettyProperty());

        tblRepos.setItems(repoData);

        System.out.println("✅ Interfaz inicializada correctamente.");
    }

    /**
     * Evento del botón “Buscar” (asociado en el FXML con onAction="#onBuscarUsuario").
     */
    @FXML
    private void onBuscarUsuario() {
        String username = txtUsername.getText().trim();

        if (username.isEmpty()) {
            lblBio.setText("⚠️ Ingrese un nombre de usuario.");
            return;
        }

        lblBio.setText("⏳ Cargando información...");
        repoData.clear();
        chartLanguages.getData().clear();

        // Llamada a la API en un hilo separado para no congelar la UI
        new Thread(() -> {
            try {
                User user = api.getUser(username);
                List<Repo> repos = api.getRepos(username);

                // Actualizar la interfaz en el hilo de JavaFX
                Platform.runLater(() -> updateUI(user, repos));

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() ->
                        lblBio.setText("❌ Error: " + e.getMessage()));
            }
        }).start();
    }

    /**
     * Actualiza todos los elementos visuales con los datos del usuario.
     */
    private void updateUI(User user, List<Repo> repos) {
        lblName.setText(user.getName() != null ? user.getName() : user.getLogin());
        lblBio.setText(user.getBio() != null ? user.getBio() : "Sin biografía");
        lblFollowers.setText("👥 Seguidores: " + user.getFollowers());
        lblFollowing.setText("➡️ Siguiendo: " + user.getFollowing());

        // Imagen de avatar
        if (user.getAvatarUrl() != null && !user.getAvatarUrl().isBlank()) {
            imgAvatar.setImage(new Image(user.getAvatarUrl(), true));
        } else {
            imgAvatar.setImage(null);
        }

        // Rellenar la tabla
        repoData.setAll(repos);

        // Generar el gráfico de lenguajes
        LanguageStats stats = LanguageStats.fromRepos(repos);
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        stats.getCounts().forEach((lang, count) ->
                pieData.add(new PieChart.Data(lang, count)));

        chartLanguages.setData(pieData);
    }

    /**
     * Permite cambiar a otra vista (FXML secundario) si se agrega más adelante.
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
