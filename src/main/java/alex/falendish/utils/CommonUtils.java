package alex.falendish.utils;

import alex.falendish.model.BookingRequest;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CommonUtils {

    private static final Random random = new Random();


    public static BigDecimal zeroIfNull(BigDecimal value) {
        return value != null ? value : BigDecimal.ZERO;
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean isPositive(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public static BigDecimal calculateRoutePrice(BookingRequest request) {
        if (request.getStartAddress() == null || request.getStartAddress().isEmpty()) {
            throw new IllegalArgumentException("Start address should be null or empty");
        }
        if (request.getDestinationAddress() == null || request.getDestinationAddress().isEmpty()) {
            throw new IllegalArgumentException("Final address should be null or empty");
        }
        return BigDecimal.valueOf(random.nextDouble() * 100);
    }

    public static VehicleCategoryType getNextVehicleCategory(VehicleCategoryType type) {
        switch (type) {
            case ECONOMY:
                return VehicleCategoryType.STANDARD;
            case STANDARD:
                return VehicleCategoryType.COMFORT;
            case COMFORT:
                return VehicleCategoryType.LUXURY;
            default:
                return null;
        }
    }

    public static long calculateVehicleDeliveryTimeout() {
        return TimeUnit.MINUTES.toMillis(random.nextLong() + 1L);
    }
}
