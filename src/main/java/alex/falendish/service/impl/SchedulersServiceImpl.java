package alex.falendish.service.impl;

import alex.falendish.dao.BookingDAO;
import alex.falendish.dao.impl.JdbcBookingDAO;
import alex.falendish.model.Vehicle;
import alex.falendish.service.SchedulersService;
import alex.falendish.service.VehicleService;
import alex.falendish.utils.BookingStatus;
import alex.falendish.utils.CommonUtils;
import alex.falendish.utils.VehicleStatus;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulersServiceImpl implements SchedulersService {

    public static final ScheduledExecutorService executors = Executors.newScheduledThreadPool(5);

    private final BookingDAO bookingDAO = new JdbcBookingDAO();
    private final VehicleService vehicleService = new VehicleServiceImpl();

    @Override
    public void scheduleUnconfirmedBookingCancellation(Long bookingId, Collection<Long> reservedVehicleIds, Long timeoutMs) {
        executors.schedule(
                tryToCancelUnconfirmedBooking(bookingId, reservedVehicleIds),
                timeoutMs,
                TimeUnit.MILLISECONDS);
    }

    @Override
    public void scheduleStartRideUserNotification(Collection<Long> vehicleIds) {
        executors.schedule(
                tryToSendUserNotification(vehicleIds),
                CommonUtils.calculateVehicleDeliveryTimeout(),
                TimeUnit.MILLISECONDS);
    }

    private Runnable tryToSendUserNotification(Collection<Long> vehicleIds) {
        return () -> {
            Collection<Vehicle> vehicles = vehicleService.findAllByIds(vehicleIds);
            // imitation of user notification (you can send email/sms)
            vehicles.forEach(vehicle -> System.out.println(vehicle.getStandByDetails()));
            vehicleService.updateStatusByIds(VehicleStatus.STANDBY, vehicleIds);
        };
    }

    private Runnable tryToCancelUnconfirmedBooking(Long bookingId, Collection<Long> reservedVehicleIds) {
        return () -> {
            bookingDAO.updateBookingStatus(BookingStatus.CANCELLED, bookingId);
            vehicleService.updateStatusByIds(VehicleStatus.IDLE, reservedVehicleIds);
        };
    }
}
