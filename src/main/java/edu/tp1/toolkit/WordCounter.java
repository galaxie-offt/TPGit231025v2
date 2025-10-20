package edu.tp1.toolkit;

import java.util.Arrays;

/**
 * Compte les mots en traitant espaces/tab, '-' et '_' comme séparateurs,
 * en retirant la ponctuation classique.
 */
public class WordCounter {

    public int count(String input) {
        // TODO: implémenter
        if (input == null) throw new IllegalArgumentException("input must not be null");
        String s = input.trim();
        if (s.isBlank()) return 0;
        // Basique pour démarrer (à améliorer)
        s = s.replaceAll("[_\\-]+", " ");
        s = s.replaceAll("\\p{Punct}", " ");
        s = s.replaceAll("\\s+", " ").trim();
        if (s.isEmpty()) return 0;
        return (int) Arrays.stream(s.split(" ")).filter(t -> !t.isBlank()).count();
    }
}
