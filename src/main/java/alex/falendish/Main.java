package alex.falendish;

import alex.falendish.dao.UserDAO;
import alex.falendish.dao.impl.JdbcUserDAO;
import alex.falendish.model.BookingRequest;
import alex.falendish.model.User;
import alex.falendish.service.BookingService;
import alex.falendish.service.impl.BookingServiceImpl;
import alex.falendish.utils.Role;
import alex.falendish.utils.UserStatus;
import alex.falendish.utils.VehicleCategoryType;

import java.time.LocalDateTime;
import java.util.Collections;

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


        UserDAO user= new JdbcUserDAO();
        user.create(new User(987654567l, "nikita@gmail.com", "098908", "Nikita", "Todo",
                Collections.singleton(Role.USER), UserStatus.ACTIVE, false, LocalDateTime.now()));
    }
}