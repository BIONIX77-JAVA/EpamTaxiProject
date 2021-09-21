package alex.falendish.dao;

import alex.falendish.model.VehicleBillingOrder;
import alex.falendish.utils.VehicleBillingOrderStatus;

import java.util.Collection;

public interface VehicleBillingOrderDAO {

    VehicleBillingOrder save(VehicleBillingOrder order);

    Collection<VehicleBillingOrder> findAllByVehicleId(Long vehicleId);

    void updateStatusByBookingId(VehicleBillingOrderStatus status, Long bookingId);
}
