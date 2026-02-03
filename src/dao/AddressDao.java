package dao;

import model.Address;
import java.sql.*;

public class AddressDao {

    public void addAddress(Address address) throws SQLException {
        String sql = "INSERT INTO addresses (user_id, street, city, province, postal_code, country) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getStreet());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getProvince());
            ps.setString(5, address.getPostalCode());
            ps.setString(6, address.getCountry());
            ps.executeUpdate();
        }
    }

    public Address findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM addresses WHERE user_id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code"),
                        rs.getString("country")
                );
            }
        }
        return null;
    }
}
