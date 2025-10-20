package edu.tp1.toolkit;

/**
 * Vérifie si une chaîne est un palindrome en ignorant
 * la casse, les espaces, la ponctuation et les accents.
 * Convention: vide/espaces => palindrome.
 */
public class PalindromeService {

    public boolean isPalindrome(String input) {
        // TODO: implémenter correctement
        if (input == null) throw new IllegalArgumentException("input must not be null");
        String s = input.trim();
        if (s.isBlank()) return true;
        // Version naïve (à remplacer)
        String rev = new StringBuilder(s).reverse().toString();
        return s.equalsIgnoreCase(rev);
    }
}
