package alex.falendish.utils;

public enum VehicleStatus {

    IDLE,           // is waiting for booking
    RESERVED,       // has been proposed for booking
    PENDING,        // moving to user start address
    STANDBY,        // waits for user at his start address
    RENTED,         // booking processing
    MAINTENANCE     // out of order or in garage
}