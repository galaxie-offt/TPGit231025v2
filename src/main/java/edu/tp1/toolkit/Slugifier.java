package edu.tp1.toolkit;

import java.text.Normalizer;
import java.util.Locale;

/**
 * Transforme une chaîne libre en slug.
 * Règles attendues (à implémenter) :
 * - minuscules
 * - accents retirés
 * - ponctuation/emoji retirés
 * - espaces et séparateurs -> '-'
 * - réduire multi '-' à un seul, trim des '-' en bord
 */
public class Slugifier {

    public String slugify(String input) {
        // TODO: implémenter
        if (input == null) throw new IllegalArgumentException("input must not be null");
        String s = input.trim();
        if (s.isBlank()) return "";
        // Valeur très simplifiée pour démarrer (à améliorer par l'étudiant·e)
        return s.toLowerCase(Locale.ROOT).replaceAll("\\s+", "-");
    }
}
