package alex.falendish.service;

import alex.falendish.model.Vehicle;
import alex.falendish.utils.VehicleCategoryType;
import alex.falendish.utils.VehicleStatus;
import alex.falendish.utils.VehicleType;

import java.math.BigDecimal;
import java.util.Collection;

public interface VehicleService {

    Collection<Vehicle> findAvailableVehicles(VehicleCategoryType categoryType, VehicleType vehicleType, int seats);

    void reserveVehicles(Collection<Vehicle> vehicles, long orderId, BigDecimal price);

    void updateStatusByIds(VehicleStatus pending, Collection<Long> boundVehicleIds);

    void updateStatusByBookingId(VehicleStatus pending, Long bookingId);

    void sendBookingRequestToVehicles(Long bookingId);

    Collection<Vehicle> findAllByIds(Collection<Long> vehicleIds);

    void activateBillingOrders(Long bookingId);
}
