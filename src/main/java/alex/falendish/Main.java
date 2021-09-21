package alex.falendish;

import alex.falendish.model.BookingRequest;
import alex.falendish.service.BookingService;
import alex.falendish.service.impl.BookingServiceImpl;
import alex.falendish.utils.VehicleCategoryType;

public class Main {

    public static void main(String[] args) {
        BookingService bookingService = new BookingServiceImpl();

        BookingRequest request = new BookingRequest();
        request.setUserId(1L);
        request.setStartAddress("Street #1");
        request.setDestinationAddress("Street #2");
        request.setCategoryType(VehicleCategoryType.STANDARD);
        request.setSeats(3);
        request.setPromoCode("TEST");
        System.out.println(bookingService.book(request));
    }
}