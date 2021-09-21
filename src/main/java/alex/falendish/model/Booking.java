package alex.falendish.model;

import alex.falendish.utils.BookingStatus;
import alex.falendish.utils.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

public class Booking {

    private Long id;
    private Long userId;
    private Long bookingRequestId;

    private Collection<Vehicle> primaryVehicles;
    private Collection<Vehicle> optionalVehicles;

    private BigDecimal totalCost;
    private BigDecimal totalPrice;

    private BigDecimal discount;
    private DiscountType discountType;
    private String promoCode;

    private BookingStatus status;

    private LocalDateTime modified;
    private LocalDateTime created;

    public Booking(Long id, Long userId, Long bookingRequestId, Collection<Vehicle> primaryVehicles,
                   Collection<Vehicle> optionalVehicles, BigDecimal totalCost, BigDecimal totalPrice,
                   BigDecimal discount, DiscountType discountType, String promoCode, BookingStatus status) {
        this.id = id;
        this.userId = userId;
        this.bookingRequestId = bookingRequestId;
        this.primaryVehicles = primaryVehicles;
        this.optionalVehicles = optionalVehicles;
        this.totalCost = totalCost;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.discountType = discountType;
        this.promoCode = promoCode;
        this.status = status;
        this.modified = LocalDateTime.now();
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public Long getBookingRequestId() {
        return bookingRequestId;
    }

    public void setBookingRequestId(Long bookingRequestId) {
        this.bookingRequestId = bookingRequestId;
    }

    public Collection<Vehicle> getPrimaryVehicles() {
        return primaryVehicles;
    }

    public void setPrimaryVehicles(Collection<Vehicle> primaryVehicles) {
        this.primaryVehicles = primaryVehicles;
    }

    public Collection<Vehicle> getOptionalVehicles() {
        return optionalVehicles;
    }

    public void setOptionalVehicles(Collection<Vehicle> optionalVehicles) {
        this.optionalVehicles = optionalVehicles;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookingRequestId=" + bookingRequestId +
                ", primaryVehicles=" + primaryVehicles +
                ", optionalVehicles=" + optionalVehicles +
                ", totalCost=" + totalCost +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                ", discountType=" + discountType +
                ", promoCode='" + promoCode + '\'' +
                ", status=" + status +
                ", modified=" + modified +
                ", created=" + created +
                '}';
    }
}
