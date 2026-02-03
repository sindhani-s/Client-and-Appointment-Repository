package dao;

import model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {

    public void recordPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (booking_id, amount, payment_time, method, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setTimestamp(3, Timestamp.valueOf(payment.getPaymentTime()));
            ps.setString(4, payment.getMethod());
            ps.setString(5, payment.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Payment> getPaymentsForBooking(int bookingId) throws SQLException {
        String sql = "SELECT * FROM payments WHERE booking_id = ?";
        List<Payment> list = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    private Payment mapRow(ResultSet rs) throws SQLException {
        return new Payment(
                rs.getInt("payment_id"),
                rs.getInt("booking_id"),
                rs.getDouble("amount"),
                rs.getTimestamp("payment_time").toLocalDateTime(),
                rs.getString("method"),
                rs.getString("status")
        );
    }
}
