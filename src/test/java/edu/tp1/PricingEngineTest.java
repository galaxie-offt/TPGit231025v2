package edu.tp1;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class PricingEngineTest {

    private final PricingEngine engine = new PricingEngine();

    @ParameterizedTest
    @EnumSource(TicketType.class)
    @DisplayName("basePrice() should return correct price for each ticket type")
    void testBasePrice(TicketType type) {
        assertEquals(type.getPrice(), engine.basePrice(type));
    }

    @Test
    @DisplayName("basePrice() should throw exception for null type")
    void testBasePriceNull() {
        assertThrows(IllegalArgumentException.class, () -> engine.basePrice(null));
    }

    @Test
    @DisplayName("computeTotal() should return 0.00 for empty basket")
    void testEmptyBasket() {
        PriceBreakdown breakdown = engine.computeTotal(Collections.emptyList(), DayOfWeek.MONDAY, false);
        assertEquals(0.00, breakdown.getTotal());
    }
    
    @Test
    @DisplayName("computeTotal() with Wednesday discount only")
    void testWednesdayDiscount() {
        List<TicketType> tickets = List.of(TicketType.ADULT, TicketType.SENIOR, TicketType.CHILD);
        PriceBreakdown breakdown = engine.computeTotal(tickets, DayOfWeek.WEDNESDAY, false);
        assertEquals(18.80, breakdown.getTotal());
    }
    
    @Test
    @DisplayName("computeTotal() with 3D surcharge only")
    void test3DSurcharge() {
        List<TicketType> tickets = List.of(TicketType.ADULT, TicketType.CHILD);
        PriceBreakdown breakdown = engine.computeTotal(tickets, DayOfWeek.MONDAY, true);
        assertEquals(20.00, breakdown.getTotal());
    }

    @Test
    @DisplayName("computeTotal() with Group discount only")
    void testGroupDiscount() {
        List<TicketType> tickets = Collections.nCopies(4, TicketType.STUDENT);
        PriceBreakdown breakdown = engine.computeTotal(tickets, DayOfWeek.MONDAY, false);
        assertEquals(28.80, breakdown.getTotal());
    }

    @ParameterizedTest
    @CsvSource({
        // On utilise des apostrophes pour que la liste de tickets soit lue comme un seul argument
        "'ADULT,CHILD',        true,  WEDNESDAY, 16.80",
        "'ADULT:4',            true,  WEDNESDAY, 36.00",
        "'STUDENT:4',          false, MONDAY,    28.80",
        "'ADULT,SENIOR,CHILD', false, WEDNESDAY, 18.80"
    })
    @DisplayName("computeTotal() should handle combinations of rules correctly")
    void testCombinedRules(String ticketsStr, boolean is3D, DayOfWeek day, double expectedTotal) {
        List<TicketType> tickets;
        // Gère la notation "TYPE:QUANTITE"
        if (ticketsStr.contains(":")) {
            String[] parts = ticketsStr.split(":");
            TicketType type = TicketType.valueOf(parts[0]);
            int count = Integer.parseInt(parts[1]);
            tickets = Collections.nCopies(count, type);
        } else {
            // Gère la notation "TYPE1,TYPE2,..." pour n'importe quel nombre de tickets
            tickets = Arrays.stream(ticketsStr.split(","))
                            .map(TicketType::valueOf)
                            .collect(Collectors.toList());
        }
        
        PriceBreakdown breakdown = engine.computeTotal(tickets, day, is3D);
        // On utilise une tolérance (delta) pour les comparaisons de doubles
        assertEquals(expectedTotal, breakdown.getTotal(), 0.01);
    }
}
