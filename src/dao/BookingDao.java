package dao;

import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {

    public void createBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (client_id, advisor_id, service_id, booking_time, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getClientId());
            ps.setInt(2, booking.getAdvisorId());
            ps.setInt(3, booking.getServiceId());
            ps.setTimestamp(4, Timestamp.valueOf(booking.getBookingTime()));
            ps.setString(5, booking.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Booking> findByClientId(int clientId) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE client_id = ?";
        List<Booking> list = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    public void updateStatus(int bookingId, String status) throws SQLException {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, bookingId);
            ps.executeUpdate();
        }
    }

    private Booking mapRow(ResultSet rs) throws SQLException {
        return new Booking(
                rs.getInt("booking_id"),
                rs.getInt("client_id"),
                rs.getInt("advisor_id"),
                rs.getInt("service_id"),
                rs.getTimestamp("booking_time").toLocalDateTime(),
                rs.getString("status")
        );
    }
}
