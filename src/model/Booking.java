package model;
import java.time.LocalDateTime;

public class Booking {
    private int bookingId;
    private int clientId;
    private int advisorId;
    private int serviceId;
    private LocalDateTime bookingTime;
    private String status;
// constructor
    public Booking() {}

    public Booking(int bookingId, int clientId, int advisorId, int serviceId,
                   LocalDateTime bookingTime, String status) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.advisorId = advisorId;
        this.serviceId = serviceId;
        this.bookingTime = bookingTime;
        this.status = status;
    }
// getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(int advisorId) {
        this.advisorId = advisorId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", clientId=" + clientId +
                ", advisorId=" + advisorId +
                ", serviceId=" + serviceId +
                ", bookingTime=" + bookingTime +
                ", status='" + status + '\'' +
                '}';
    }
}
