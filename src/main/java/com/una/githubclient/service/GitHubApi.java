package com.una.githubclient.service;

import com.una.githubclient.domain.Repo;
import com.una.githubclient.domain.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Servicio responsable de consumir la API pública de GitHub
 * y transformar las respuestas JSON en objetos Java (User, Repo).
 */
public class GitHubApi {

    // Mapper configurado para ignorar campos desconocidos
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final HttpClient http = HttpClient.newHttpClient();

    // Token opcional (para aumentar el límite de peticiones)
    private final String token = System.getenv("GITHUB_TOKEN");

    /**
     * Obtiene la información del usuario desde la API de GitHub.
     *
     * @param username nombre del usuario (login)
     * @return objeto User con los datos del perfil
     * @throws Exception si hay errores HTTP o de red
     */
    public User getUser(String username) throws Exception {
        HttpRequest req = buildRequest("https://api.github.com/users/" + username);
        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() == 404)
            throw new RuntimeException("Usuario no encontrado: " + username);
        if (res.statusCode() >= 300)
            throw new RuntimeException("Error HTTP " + res.statusCode());

        return MAPPER.readValue(res.body(), User.class);
    }

    /**
     * Obtiene la lista de repos públicos de un usuario.
     *
     * @param username nombre del usuario
     * @return lista de repositorios
     * @throws Exception si hay errores HTTP o de red
     */
    public List<Repo> getRepos(String username) throws Exception {
        HttpRequest req = buildRequest(
                "https://api.github.com/users/" + username + "/repos?per_page=100&sort=updated"
        );
        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() >= 300)
            throw new RuntimeException("Error HTTP " + res.statusCode());

        return MAPPER.readValue(res.body(), new TypeReference<>() {});
    }

    /**
     * Crea una solicitud HTTP estándar para GitHub con headers comunes.
     */
    private HttpRequest buildRequest(String url) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .header("User-Agent", "github-client-una");

        if (token != null && !token.isBlank()) {
            builder.header("Authorization", "Bearer " + token);
        }

        return builder.GET().build();
    }
}
