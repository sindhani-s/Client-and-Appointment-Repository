package service;

import dao.PaymentDao;
import model.Payment;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public boolean recordPayment(int bookingId, double amount, String method) {
        try {
            Payment payment = new Payment();
            payment.setBookingId(bookingId);
            payment.setAmount(amount);
            payment.setMethod(method);
            payment.setStatus("PAID");
            payment.setPaymentTime(LocalDateTime.now());

            paymentDao.recordPayment(payment);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Payment> getPaymentsForBooking(int bookingId) {
        try {
            return paymentDao.getPaymentsForBooking(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
