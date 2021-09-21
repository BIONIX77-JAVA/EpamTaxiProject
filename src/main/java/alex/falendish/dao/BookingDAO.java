package alex.falendish.dao;

import alex.falendish.model.Booking;
import alex.falendish.utils.BookingStatus;

import java.util.Collection;

public interface BookingDAO {

    Collection<Long> findAllBoundVehicleIds(Long bookingId);

    void updateBookingStatus(BookingStatus status, Long bookingId);

    Booking create(Booking booking);
}
