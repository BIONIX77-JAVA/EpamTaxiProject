package alex.falendish.dao.impl;

import alex.falendish.configuration.ConnectionPool;
import alex.falendish.configuration.CustomConnectionPool;
import alex.falendish.dao.BookingRequestDAO;
import alex.falendish.model.BookingRequest;

import java.sql.*;
import java.util.Objects;

public class JdbcBookingRequestDAO implements BookingRequestDAO {

    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    @Override
    public BookingRequest insert(BookingRequest request) {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into booking_requests (user_id, start_address, destination_address, category_type, vehicle_type, seats, promo_code) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, request.getUserId());
            ps.setString(2, request.getStartAddress());
            ps.setString(3, request.getDestinationAddress());
            ps.setString(4, request.getCategoryType().name());
            ps.setString(5, Objects.isNull(request.getVehicleType()) ? null : request.getVehicleType().name());
            ps.setInt(6, request.getSeats());
            ps.setString(7, request.getPromoCode());

            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Creating BookingRequest failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                request.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating BookingRequest failed, no ID obtained.");
            }
            return request;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
