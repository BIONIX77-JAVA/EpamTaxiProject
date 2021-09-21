package alex.falendish.service;

import alex.falendish.model.Booking;
import alex.falendish.model.BookingRequest;

public interface BookingService {

    Booking book(BookingRequest request);

    void confirmBooking(Long bookingId);

    void cancelBooking(Long bookingId);

    void startRide(Long bookingId);

    void terminateRide(Long bookingId);

}
