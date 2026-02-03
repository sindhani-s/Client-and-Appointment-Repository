package model;
import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private int bookingId;
    private double amount;
    private LocalDateTime paymentTime;
    private String method;
    private String status;
// constructor (no argument)
    public Payment() {}
// fully argumented constructor
    public Payment(int paymentId, int bookingId, double amount,
                   LocalDateTime paymentTime, String method, String status) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentTime = paymentTime;
        this.method = method;
        this.status = status;
    }
// getters and setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", paymentTime=" + paymentTime +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
