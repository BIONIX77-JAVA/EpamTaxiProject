package alex.falendish.utils;

import java.math.BigDecimal;

public enum VehicleType {

    SEDAN(4, 20),
    MINIVAN(7, 25),
    BUS(20, 30);

    private final int seats;
    private final int bookingFee;

    VehicleType(int seats, int bookingFee) {
        this.seats = seats;
        this.bookingFee = bookingFee;
    }

    public int getSeats() {
        return seats;
    }

    public BigDecimal getBookingFee() {
        return BigDecimal.valueOf(bookingFee);
    }
}