package com.una.githubclient.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;

/**
 * Representa un repositorio público obtenido de la API de GitHub.
 * Incluye propiedades JavaFX para vincular directamente con TableView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {

    // --- Propiedades observables (JavaFX) ---
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty language = new SimpleStringProperty();
    private final IntegerProperty stargazers = new SimpleIntegerProperty();
    private final StringProperty updatedAtPretty = new SimpleStringProperty();

    // ------------------------------------------------------

    // --- Setters usados por Jackson para mapear el JSON ---

    public void setName(String n) { name.set(n); }

    public void setDescription(String d) { description.set(d); }

    public void setLanguage(String l) { language.set(l); }

    @JsonProperty("stargazers_count")
    public void setStargazers(int s) { stargazers.set(s); }

    @JsonProperty("updated_at")
    public void setUpdatedAtIso(String iso) {
        // Este método transforma la fecha ISO de GitHub a formato legible
        updatedAtPretty.set(com.una.githubclient.util.Formatter.prettyDateTime(iso));
    }

    // ------------------------------------------------------

    // --- Getters tipo Property (para TableView) ---

    public StringProperty nameProperty() { return name; }

    public StringProperty descriptionProperty() { return description; }

    public StringProperty languageProperty() { return language; }

    public IntegerProperty stargazersProperty() { return stargazers; }

    public StringProperty updatedAtPrettyProperty() { return updatedAtPretty; }

    // ------------------------------------------------------

    // --- Métodos de lectura simple ---

    public String getName() { return name.get(); }

    public String getDescription() { return description.get(); }

    public String getLanguage() { return language.get(); }

    public int getStargazers() { return stargazers.get(); }

    public String getUpdatedAtPretty() { return updatedAtPretty.get(); }
}
