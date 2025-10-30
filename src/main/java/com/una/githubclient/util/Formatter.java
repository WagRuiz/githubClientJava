package com.una.githubclient.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidades para formatear texto y fechas ISO-8601 devueltas por la API de GitHub.
 */
public class Formatter {

    /**
     * Convierte una fecha en formato ISO-8601 (ej: 2025-10-29T20:25:15Z)
     * a un formato legible simple (ej: 2025-10-29 20:25:15).
     *
     * @param iso cadena ISO proveniente de la API
     * @return fecha formateada sin milisegundos, o el valor original si hay error
     */
    public static String prettyDateTime(String iso) {
        try {
            OffsetDateTime odt = OffsetDateTime.parse(iso);
            // Puedes cambiar el formato según prefieras
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return odt.toLocalDateTime().format(fmt);
        } catch (Exception e) {
            return iso; // si hay error, devuelve el valor tal cual
        }
    }

    /**
     * Devuelve el primer valor no nulo ni vacío entre dos cadenas.
     * Similar al operador SQL COALESCE.
     *
     * @param a primera opción
     * @param b segunda opción (valor por defecto)
     * @return la primera cadena no vacía
     */
    public static String coalesce(String a, String b) {
        return (a == null || a.isBlank()) ? b : a;
    }
}
