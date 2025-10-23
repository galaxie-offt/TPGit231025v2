package edu.tp1;

public enum TicketType {
    ADULT(10.00),
    CHILD(6.00),
    SENIOR(7.50),
    STUDENT(8.00);

    private final double price;

    TicketType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
