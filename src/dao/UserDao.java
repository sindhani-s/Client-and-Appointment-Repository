package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (full_name, email, password_hash, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }
        }
        return null;
    }

    public List<User> findAllByRole(String role) throws SQLException {
        String sql = "SELECT * FROM users WHERE role = ?";
        List<User> users = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(mapRow(rs));
            }
        }
        return users;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("password_hash"),
                rs.getString("role")
        );
    }
}
