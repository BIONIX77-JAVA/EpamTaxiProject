package alex.falendish.dao.impl;

import alex.falendish.configuration.ConnectionPool;
import alex.falendish.configuration.CustomConnectionPool;
import alex.falendish.dao.PromoCodeDAO;
import alex.falendish.model.PromoCode;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class JdbcPromoCodeDAO implements PromoCodeDAO {

    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    @Override
    public PromoCode create(PromoCode promoCode) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO promo_codes (value, percent, valid_from, valid_to) \n" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, promoCode.getValue());
            ps.setBigDecimal(2, promoCode.getPercent());
            ps.setDate(3, Objects.isNull(promoCode.getValidFrom()) ? null : Date.valueOf(promoCode.getValidFrom()));
            ps.setDate(4, Objects.isNull(promoCode.getValidTo()) ? null : Date.valueOf(promoCode.getValidTo()));

            ps.executeUpdate();
            return promoCode;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<PromoCode> findOneActive(String promoCode) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT PC.value, PC.percent, PC.valid_from, PC.valid_to\n" +
                "FROM promo_codes PC\n" +
                "WHERE PC.valid_from <= now() AND PC.valid_to >= now()\n" +
                "AND PC.value = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, promoCode);

            ResultSet rs = ps.executeQuery();
            PromoCode found = null;
            if (rs.next()) {
                found = mapToPromoCode(rs);
            }
            return Optional.ofNullable(found);
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private PromoCode mapToPromoCode(ResultSet rs) throws SQLException {
        PromoCode promoCode = new PromoCode();
        promoCode.setValue(rs.getString("value"));
        promoCode.setPercent(rs.getBigDecimal("percent"));
        Date validFrom = rs.getDate("valid_from");
        promoCode.setValidFrom(Objects.isNull(validFrom) ? null : validFrom.toLocalDate());
        Date validTo = rs.getDate("valid_to");
        promoCode.setValidTo(Objects.isNull(validTo) ? null : validTo.toLocalDate());
        return promoCode;
    }
}
