package alex.falendish.model;

import alex.falendish.utils.Identifiable;
import alex.falendish.utils.Role;
import alex.falendish.utils.VehicleCategoryType;
import alex.falendish.utils.VehicleType;

import java.time.LocalDateTime;

public class BookingRequest implements Identifiable<Long> {

    private Long id;
    private Long userId;

    private String startAddress;
    private String destinationAddress;

    private VehicleCategoryType categoryType;
    private VehicleType vehicleType;
    private int seats;

    private String promoCode;

    private LocalDateTime created;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public void setRole(Role unknown) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public VehicleCategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(VehicleCategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
