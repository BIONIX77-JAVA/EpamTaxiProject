package alex.falendish.dao.impl;

import alex.falendish.configuration.ConnectionPool;
import alex.falendish.configuration.CustomConnectionPool;
import alex.falendish.dao.BookingDAO;
import alex.falendish.model.Booking;
import alex.falendish.model.Vehicle;
import alex.falendish.utils.BookingStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdbcBookingDAO implements BookingDAO {

    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    @Override
    public Collection<Long> findAllBoundVehicleIds(Long bookingId) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT DISTINCT BV.vehicle_id FROM BOOKINGS_VEHICLES BV WHERE BV.booking_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, bookingId);

            Collection<Long> vehicleIds = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicleIds.add(rs.getLong(1));
            }
            return vehicleIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void updateBookingStatus(BookingStatus status, Long bookingId) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE BOOKINGS SET status = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status.name());
            ps.setLong(2, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Booking create(Booking booking) {
        Booking saved = insertNewBooking(booking);
        insertMappedVehicles(booking);
        return saved;
    }

    private Booking insertNewBooking(Booking booking) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO BOOKINGS (id, user_id, booking_request_id, total_cost, total_price, discount, discount_type, promo_code, status)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, booking.getId());
            ps.setLong(2, booking.getUserId());
            ps.setLong(3, booking.getBookingRequestId());
            ps.setBigDecimal(4, booking.getTotalCost());
            ps.setBigDecimal(5, booking.getTotalPrice());
            ps.setBigDecimal(6, booking.getDiscount());
            ps.setString(7, Objects.isNull(booking.getDiscountType()) ? null : booking.getDiscountType().name());
            ps.setString(8, booking.getPromoCode());
            ps.setString(9, booking.getStatus().name());

            ps.executeUpdate();
            return booking;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void insertMappedVehicles(Booking booking) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO BOOKINGS_VEHICLES (booking_id, vehicle_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            Set<Long> vehicleIds = Stream.concat(booking.getPrimaryVehicles().stream(), booking.getOptionalVehicles().stream())
                    .map(Vehicle::getId)
                    .collect(Collectors.toSet());
            for (Long vehicleId : vehicleIds) {
                ps.setLong(1, booking.getId());
                ps.setLong(2, vehicleId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Inserting mapped vehicles failed");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
