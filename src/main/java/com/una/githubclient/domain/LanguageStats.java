package com.una.githubclient.domain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que genera estadísticas de uso de lenguajes
 * a partir de la lista de repositorios obtenidos de la API de GitHub.
 */
public class LanguageStats {

    // Mapa que almacena el conteo de cada lenguaje
    private final Map<String, Long> counts;

    /**
     * Constructor principal.
     * @param counts mapa de lenguajes con su cantidad de ocurrencias.
     */
    public LanguageStats(Map<String, Long> counts) {
        this.counts = counts;
    }

    /**
     * Obtiene el mapa completo de conteos.
     */
    public Map<String, Long> getCounts() {
        return counts;
    }

    /**
     * Crea una instancia de LanguageStats a partir de la lista de repositorios.
     * Agrupa los lenguajes y cuenta cuántas veces aparece cada uno.
     *
     * @param repos lista de repositorios obtenidos de la API
     * @return objeto LanguageStats con los conteos calculados
     */
    public static LanguageStats fromRepos(List<Repo> repos) {
        if (repos == null || repos.isEmpty()) {
            return new LanguageStats(Collections.emptyMap());
        }

        Map<String, Long> grouped = repos.stream()
                .map(r -> Optional.ofNullable(r.getLanguage()).orElse("Otros"))
                .collect(Collectors.groupingBy(lang -> lang, Collectors.counting()));

        return new LanguageStats(grouped);
    }

    /**
     * Devuelve una lista con los porcentajes de uso por lenguaje,
     * ordenados de mayor a menor.
     *
     * @return lista de pares (lenguaje, porcentaje)
     */
    public List<Map.Entry<String, Double>> percentages() {
        long total = counts.values().stream().mapToLong(Long::longValue).sum();
        if (total == 0) return Collections.emptyList();

        return counts.entrySet().stream()
                .map(e -> Map.entry(e.getKey(), (e.getValue() * 100.0) / total))
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());
    }
}
