package alex.falendish.utils;

import java.math.BigDecimal;

public enum VehicleCategoryType {

    ECONOMY(0),
    STANDARD(25),
    COMFORT(50),
    LUXURY(75);

    private final int bookingFee;

    VehicleCategoryType(int fee) {
        this.bookingFee = fee;
    }

    public BigDecimal getBookingFee() {
        return BigDecimal.valueOf(bookingFee);
    }
}
