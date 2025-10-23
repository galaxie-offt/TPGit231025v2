package edu.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceBreakdownTest {

    @Test
    @DisplayName("Le constructeur et les getters doivent fonctionner correctement")
    void testConstructorAndGetters() {
        // 1. Création d'une instance avec des valeurs connues
        PriceBreakdown breakdown = new PriceBreakdown(100.0, 20.0, 5.0, 10.0, 75.0);

        // 2. Vérification que chaque getter retourne la bonne valeur
        assertEquals(100.0, breakdown.getSubtotal(), "Le sous-total est incorrect");
        assertEquals(20.0, breakdown.getWednesdayDiscount(), "La réduction du mercredi est incorrecte");
        assertEquals(5.0, breakdown.getThreeDSurcharge(), "Le supplément 3D est incorrect");
        assertEquals(10.0, breakdown.getGroupDiscount(), "La réduction de groupe est incorrecte");
        assertEquals(75.0, breakdown.getTotal(), "Le total est incorrect");
    }

    @Test
    @DisplayName("La méthode toString() doit contenir les valeurs des champs")
    void testToString() {
        // 1. Création d'une instance
        PriceBreakdown breakdown = new PriceBreakdown(100.5, 20.5, 5.5, 10.5, 75.5);

        // 2. Récupération de la représentation en chaîne de caractères
        String resultString = breakdown.toString();

        // 3. Vérification que la chaîne contient bien les informations attendues
        // C'est plus robuste que de tester une correspondance exacte
        assertTrue(resultString.contains("subtotal=100.5"));
        assertTrue(resultString.contains("wednesdayDiscount=20.5"));
        assertTrue(resultString.contains("threeDSurcharge=5.5"));
        assertTrue(resultString.contains("groupDiscount=10.5"));
        assertTrue(resultString.contains("total=75.5"));
    }
}