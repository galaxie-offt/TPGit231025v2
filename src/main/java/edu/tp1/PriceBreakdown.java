package edu.tp1;

import java.util.StringJoiner;

public final class PriceBreakdown {
    private final double subtotal;
    private final double wednesdayDiscount;
    private final double threeDSurcharge;
    private final double groupDiscount;
    private final double total;

    public PriceBreakdown(double subtotal, double wednesdayDiscount, double threeDSurcharge, double groupDiscount, double total) {
        this.subtotal = subtotal;
        this.wednesdayDiscount = wednesdayDiscount;
        this.threeDSurcharge = threeDSurcharge;
        this.groupDiscount = groupDiscount;
        this.total = total;
    }

    public double getSubtotal() { return subtotal; }
    public double getWednesdayDiscount() { return wednesdayDiscount; }
    public double getThreeDSurcharge() { return threeDSurcharge; }
    public double getGroupDiscount() { return groupDiscount; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return new StringJoiner(", ", PriceBreakdown.class.getSimpleName() + "[", "]")
                .add("subtotal=" + subtotal)
                .add("wednesdayDiscount=" + wednesdayDiscount)
                .add("threeDSurcharge=" + threeDSurcharge)
                .add("groupDiscount=" + groupDiscount)
                .add("total=" + total)
                .toString();
    }
}
