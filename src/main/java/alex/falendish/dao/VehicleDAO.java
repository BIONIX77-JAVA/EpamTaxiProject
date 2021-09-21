package alex.falendish.dao;

import alex.falendish.model.Vehicle;
import alex.falendish.utils.VehicleCategoryType;
import alex.falendish.utils.VehicleStatus;
import alex.falendish.utils.VehicleType;

import java.util.Collection;
import java.util.Optional;

public interface VehicleDAO {

    Vehicle create(Vehicle vehicle);

    Optional<Vehicle> findAvailableVehicle(VehicleCategoryType categoryType, VehicleType type);

    void updateStatusByBookingId(VehicleStatus status, Long bookingId);

    void updateStatusByIds(VehicleStatus status, Collection<Long> boundVehicleIds);

    Collection<Vehicle> findAllByIds(Collection<Long> vehicleIds);
}
