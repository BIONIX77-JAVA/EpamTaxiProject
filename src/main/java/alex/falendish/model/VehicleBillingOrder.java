package alex.falendish.model;

import alex.falendish.utils.VehicleBillingOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleBillingOrder {

    private Long id;
    private Long vehicleId;
    private Long bookingId;
    private BigDecimal price;

    private VehicleBillingOrderStatus status;

    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public VehicleBillingOrderStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleBillingOrderStatus status) {
        this.status = status;
    }
}
