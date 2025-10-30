package com.una.githubclient.util;

/**
 * Representa la información de límite de peticiones (rate limit) de la API de GitHub.
 * 
 * GitHub envía en las cabeceras de respuesta:
 *  - X-RateLimit-Remaining : número de peticiones restantes.
 *  - X-RateLimit-Reset     : timestamp (epoch seconds) del momento de reinicio.
 *
 * Esta clase se puede usar para mostrar advertencias o evitar llamadas innecesarias.
 */
public record RateLimitInfo(int remaining, long resetEpochSeconds) {

    /**
     * Devuelve una instancia vacía cuando no se encuentran cabeceras de límite.
     *
     * @return RateLimitInfo con valores -1 / -1.
     */
    public static RateLimitInfo empty() {
        return new RateLimitInfo(-1, -1);
    }
}
