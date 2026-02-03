package dao;

import model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {

    public void createService(Service service) throws SQLException {
        String sql = "INSERT INTO services (name, description, base_price) VALUES (?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getName());
            ps.setString(2, service.getDescription());
            ps.setDouble(3, service.getBasePrice());
            ps.executeUpdate();
        }
    }

    public List<Service> getAllServices() throws SQLException {
        String sql = "SELECT * FROM services";
        List<Service> list = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Service(
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("base_price")
                ));
            }
        }
        return list;
    }
}
