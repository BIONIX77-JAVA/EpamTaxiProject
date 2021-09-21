package alex.falendish.model;

import alex.falendish.utils.VehicleCategoryType;
import alex.falendish.utils.VehicleStatus;
import alex.falendish.utils.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static alex.falendish.utils.CommonUtils.zeroIfNull;

public class Vehicle {

    private Long id;

    private String brand;
    private String model;

    private String registrationNumber;
    private String color;

    private VehicleType vehicleType;
    private VehicleCategoryType categoryType;
    private VehicleStatus vehicleStatus;

    private LocalDateTime created;

    public BigDecimal getBookingPrice(BigDecimal routePrice) {
        return zeroIfNull(categoryType.getBookingFee())
                .add(zeroIfNull(vehicleType.getBookingFee()))
                .add(zeroIfNull(routePrice));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleCategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(VehicleCategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getStandByDetails() {
        return String.join(" ", brand, model, registrationNumber, color);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", vehicleType=" + vehicleType +
                ", categoryType=" + categoryType +
                ", vehicleStatus=" + vehicleStatus +
                ", created=" + created +
                '}';
    }
}
