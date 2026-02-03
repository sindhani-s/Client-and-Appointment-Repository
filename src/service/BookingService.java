package service;

import dao.BookingDao;
import model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {

    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public boolean createBooking(int clientId, int advisorId, int serviceId, LocalDateTime time) {
        try {
            Booking booking = new Booking();
            booking.setClientId(clientId);
            booking.setAdvisorId(advisorId);
            booking.setServiceId(serviceId);
            booking.setBookingTime(time);
            booking.setStatus("SCHEDULED");

            bookingDao.createBooking(booking);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getBookingsForClient(int clientId) {
        try {
            return bookingDao.findByClientId(clientId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean completeBooking(int bookingId) {
        try {
            bookingDao.updateStatus(bookingId, "COMPLETED");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
