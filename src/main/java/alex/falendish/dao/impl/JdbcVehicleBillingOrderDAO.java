package alex.falendish.dao.impl;

import alex.falendish.configuration.ConnectionPool;
import alex.falendish.configuration.CustomConnectionPool;
import alex.falendish.dao.VehicleBillingOrderDAO;
import alex.falendish.model.VehicleBillingOrder;
import alex.falendish.utils.VehicleBillingOrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class JdbcVehicleBillingOrderDAO implements VehicleBillingOrderDAO {

    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    @Override
    public VehicleBillingOrder save(VehicleBillingOrder order) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO VEHICLE_BILLING_ORDERS (vehicle_id, booking_id, price, status)\n" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getVehicleId());
            ps.setLong(2, order.getBookingId());
            ps.setBigDecimal(3, order.getPrice());
            ps.setString(4, order.getStatus().name());

            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Creating vehicle bill order failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating vehicle bill order failed, no ID obtained.");
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Collection<VehicleBillingOrder> findAllByVehicleId(Long vehicleId) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT VBO.id, VBO.vehicle_id, VBO.booking_id, VBO.price, VBO.status, VBO.created\n" +
                "FROM VEHICLE_BILLING_ORDERS VBO\n" +
                "WHERE VBO.vehicle_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, vehicleId);

            ResultSet rs = ps.executeQuery();
            Collection<VehicleBillingOrder> orders = new ArrayList<>();
            if (rs.next()) {
                orders.add(mapToVehicleBillingOrder(rs));
            }
            return orders;
        } catch (SQLException e) {
            return Collections.emptyList();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void updateStatusByBookingId(VehicleBillingOrderStatus status, Long bookingId) {
        String sql = "UPDATE VEHICLE_BILLING_ORDERS SET status = ? WHERE booking_id = ?";
        Connection connection = connectionPool.getConnection();
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

    private VehicleBillingOrder mapToVehicleBillingOrder(ResultSet rs) throws SQLException {
        VehicleBillingOrder order = new VehicleBillingOrder();
        order.setId(rs.getLong("id"));
        order.setVehicleId(rs.getLong("vehicle_id"));
        order.setBookingId(rs.getLong("booking_id"));
        order.setPrice(rs.getBigDecimal("price"));
        order.setStatus(VehicleBillingOrderStatus.valueOf(rs.getString("status")));
        Timestamp created = rs.getTimestamp("created");
        order.setCreated(Objects.isNull(created) ? null : created.toLocalDateTime());
        return order;
    }
}
