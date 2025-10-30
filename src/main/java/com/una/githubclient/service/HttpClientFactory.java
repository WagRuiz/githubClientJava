package com.una.githubclient.service;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * Fábrica para crear instancias configuradas de HttpClient.
 * Permite centralizar configuraciones de tiempo de espera, versiones o políticas de conexión.
 */
public class HttpClientFactory {

    // Cliente reutilizable (singleton)
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    /**
     * Devuelve una instancia única (reutilizable) del cliente HTTP.
     * Ideal para ser usada por todas las clases de servicio.
     *
     * @return instancia de HttpClient configurada
     */
    public static HttpClient getClient() {
        return CLIENT;
    }

    /**
     * Permite crear un cliente HTTP personalizado (si se necesita otro timeout o configuración).
     *
     * @param timeoutSeconds tiempo máximo de conexión en segundos
     * @return nuevo HttpClient con timeout específico
     */
    public static HttpClient createCustomClient(int timeoutSeconds) {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(timeoutSeconds))
                .build();
    }
}
