package edu.tp1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.util.List;

public class PricingEngine {

    public double basePrice(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("TicketType cannot be null.");
        }
        return type.getPrice();
    }

    public PriceBreakdown computeTotal(List<TicketType> tickets, DayOfWeek day, boolean is3D) {
        if (tickets == null || day == null) {
            throw new IllegalArgumentException("Tickets list and day cannot be null.");
        }

        double subtotal = tickets.stream()
                                 .mapToDouble(this::basePrice)
                                 .sum();

        double priceAfterWednesdayDiscount = subtotal;
        double wednesdayDiscount = 0;
        if (day == DayOfWeek.WEDNESDAY) {
            wednesdayDiscount = roundToCents(subtotal * 0.20);
            priceAfterWednesdayDiscount = subtotal - wednesdayDiscount;
        }
        
        double threeDSurcharge = 0;
        if (is3D) {
            threeDSurcharge = tickets.size() * 2.00;
        }
        double priceAfter3D = priceAfterWednesdayDiscount + threeDSurcharge;

        double groupDiscount = 0;
        if (tickets.size() >= 4) {
            groupDiscount = roundToCents(priceAfter3D * 0.10);
        }
        
        double total = priceAfter3D - groupDiscount;

        return new PriceBreakdown(
            roundToCents(subtotal),
            wednesdayDiscount,
            threeDSurcharge,
            groupDiscount,
            roundToCents(total)
        );
    }

    private double roundToCents(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
