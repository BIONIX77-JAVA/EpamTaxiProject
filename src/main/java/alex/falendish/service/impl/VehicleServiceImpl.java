package alex.falendish.service.impl;

import alex.falendish.dao.VehicleBillingOrderDAO;
import alex.falendish.dao.VehicleDAO;
import alex.falendish.dao.impl.JdbcVehicleBillingOrderDAO;
import alex.falendish.dao.impl.JdbcVehicleDAO;
import alex.falendish.exception.InsufficientVehiclesNumberException;
import alex.falendish.model.Vehicle;
import alex.falendish.model.VehicleBillingOrder;
import alex.falendish.service.VehicleService;
import alex.falendish.utils.VehicleBillingOrderStatus;
import alex.falendish.utils.VehicleCategoryType;
import alex.falendish.utils.VehicleStatus;
import alex.falendish.utils.VehicleType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleDAO vehicleDAO = new JdbcVehicleDAO();
    private final VehicleBillingOrderDAO billingOrderDAO = new JdbcVehicleBillingOrderDAO();

    private static VehicleType getVehicleType(int seats) {
        return Arrays.stream(VehicleType.values())
                .sorted(Comparator.comparing(VehicleType::getSeats))
                .filter(v -> v.getSeats() >= seats)
                .findFirst()
                .orElse(VehicleType.BUS);
    }

    private static Collection<VehicleType> getSuitableVehicleTypes(VehicleType vehicleType, int seats) {
        Collection<VehicleType> vehicleTypes = new ArrayList<>();
        do {
            VehicleType type = vehicleType == null
                    ? getVehicleType(seats)
                    : vehicleType;
            vehicleTypes.add(type);
            if (seats > type.getSeats()) {
                seats -= type.getSeats();
            } else {
                seats = 0;
            }
        } while (seats > 0);
        return vehicleTypes;
    }

    @Override
    public Collection<Vehicle> findAvailableVehicles(VehicleCategoryType categoryType, VehicleType vehicleType, int seats) {
        if (seats < 1) {
            throw new IllegalArgumentException("Number of booked seats should grater than 0");
        }
        Collection<VehicleType> suitableVehicleTypes = getSuitableVehicleTypes(vehicleType, seats);

        Collection<Vehicle> vehicles = new ArrayList<>(suitableVehicleTypes.size());
        suitableVehicleTypes
                .forEach(type -> vehicleDAO.findAvailableVehicle(categoryType, type).ifPresent(vehicles::add));
        if (vehicles.size() == suitableVehicleTypes.size()) {
            return vehicles;
        }
        throw new InsufficientVehiclesNumberException("Number of available cars is less than required");
    }

    @Override
    public void reserveVehicles(Collection<Vehicle> vehicles, long bookingId, BigDecimal price) {
        vehicleDAO.updateStatusByBookingId(VehicleStatus.RESERVED, bookingId);
        if (vehicles != null && !vehicles.isEmpty()) {
            vehicles.forEach(vehicle -> {
                VehicleBillingOrder order = new VehicleBillingOrder();
                order.setVehicleId(vehicle.getId());
                order.setBookingId(bookingId);
                order.setPrice(price);
                order.setStatus(VehicleBillingOrderStatus.RESERVED);
                billingOrderDAO.save(order);
            });
        }
    }

    @Override
    public void updateStatusByIds(VehicleStatus status, Collection<Long> boundVehicleIds) {
        vehicleDAO.updateStatusByIds(status, boundVehicleIds);
    }

    @Override
    public void updateStatusByBookingId(VehicleStatus status, Long bookingId) {
        vehicleDAO.updateStatusByBookingId(status, bookingId);
    }

    @Override
    public void sendBookingRequestToVehicles(Long bookingId) {
        // todo implement
    }

    @Override
    public Collection<Vehicle> findAllByIds(Collection<Long> vehicleIds) {
        return vehicleDAO.findAllByIds(vehicleIds);
    }

    @Override
    public void activateBillingOrders(Long bookingId) {
        billingOrderDAO.updateStatusByBookingId(VehicleBillingOrderStatus.RENTED, bookingId);
    }
}
