package application;

import dao.*;
import model.*;
import service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // DAO objects
        UserDao userDao = new UserDao();
        BookingDao bookingDao = new BookingDao();
        PaymentDao paymentDao = new PaymentDao();
        ServiceDao serviceDao = new ServiceDao();

        // Service objects
        UserService userService = new UserService(userDao);
        BookingService bookingService = new BookingService(bookingDao);
        PaymentService paymentService = new PaymentService(paymentDao);
        ServiceService serviceService = new ServiceService(serviceDao);

        System.out.println("\n*** Welcome to WealthCare Client & Appointment System ***");
        System.out.println("This system helps you book appointments, make payments, and connect with advisors.");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1 → Register as a new user");
            System.out.println("2 → Login to your account");
            System.out.println("3 → Exit");
            System.out.print("Enter your choice (1, 2, or 3): ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("\n--- New User Registration ---");
                System.out.print("Full Name: ");
                String name = sc.nextLine();

                System.out.print("Email Address: ");
                String email = sc.nextLine();

                System.out.print("Create a Password: ");
                String password = sc.nextLine();

                System.out.println("Choose your role:");
                System.out.println("1 → CLIENT");
                System.out.println("2 → ADVISOR");
                System.out.println("3 → CSR");
                System.out.print("Enter role number: ");
                int roleChoice = sc.nextInt();
                sc.nextLine();

                String role;
                if (roleChoice == 1) {
                    role = "CLIENT";
                } else if (roleChoice == 2) {
                    role = "ADVISOR";
                } else if (roleChoice == 3) {
                    role = "CSR";
                } else {
                    role = "CLIENT";
                }

                boolean ok = userService.registerUser(name, email, password, role);
                if (ok) System.out.println("✅ Registration successful!");
                else System.out.println(" Registration failed. Try again.");

            } else if (choice == 2) {
                System.out.println("\n--- Login ---");
                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                User user = userService.login(email, password);

                if (user == null) {
                    System.out.println(" Login failed. Please check your credentials.");
                    continue;
                }

                System.out.println("\n✅ Login successful!");
                System.out.println("Welcome, " + user.getFullName() + " (" + user.getRole() + ")");

                if (user.getRole().equals("CLIENT")) {
                    clientMenu(sc, user, bookingService, paymentService, serviceService);
                } else if (user.getRole().equals("ADVISOR")) {
                    advisorMenu(sc, user, bookingService);
                } else {
                    System.out.println("CSR dashboard is under development.");
                }

            } else if (choice == 3) {
                System.out.println("Thank you for using WealthCare. Goodbye!");
                break;
            }
        }
    }

    private static void clientMenu(Scanner sc, User user,
                                   BookingService bookingService,
                                   PaymentService paymentService,
                                   ServiceService serviceService) {

        while (true) {
            System.out.println("\n--- Client Dashboard ---");
            System.out.println("1 → View Available Services");
            System.out.println("2 → Book a Service");
            System.out.println("3 → View My Bookings");
            System.out.println("4 → Make a Payment");
            System.out.println("5 → Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                List<Service> services = serviceService.getAllServices();
                System.out.println("\nAvailable Services:");
                for (Service s : services) {
                    System.out.println(s.getServiceId() + ". " + s.getName() + " - $" + s.getBasePrice());
                }

            } else if (choice == 2) {
                System.out.print("Enter Advisor ID: ");
                int advisorId = sc.nextInt();

                System.out.print("Enter Service ID: ");
                int serviceId = sc.nextInt();

                LocalDateTime time = LocalDateTime.now().plusDays(1);

                boolean ok = bookingService.createBooking(user.getUserId(), advisorId, serviceId, time);
                if (ok) System.out.println("✅ Booking created for: " + time);
                else System.out.println(" Booking failed.");

            } else if (choice == 3) {
                List<Booking> bookings = bookingService.getBookingsForClient(user.getUserId());
                System.out.println("\nYour Bookings:");
                for (Booking b : bookings) {
                    System.out.println("Booking #" + b.getBookingId() + " | " + b.getBookingTime() + " | " + b.getStatus());
                }

            } else if (choice == 4) {
                System.out.print("Enter Booking ID: ");
                int bookingId = sc.nextInt();

                System.out.print("Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("Payment Method (CARD / E-TRANSFER): ");
                String method = sc.nextLine();

                boolean ok = paymentService.recordPayment(bookingId, amount, method);
                if (ok) System.out.println("✅ Payment recorded.");
                else System.out.println(" Payment failed.");

            } else if (choice == 5) {
                System.out.println("Logging out...");
                break;
            }
        }
    }

    private static void advisorMenu(Scanner sc, User user, BookingService bookingService) {
        while (true) {
            System.out.println("\n--- Advisor Dashboard ---");
            System.out.println("1 → View My Clients' Bookings");
            System.out.println("2 → Mark Booking as Completed");
            System.out.println("3 → Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                List<Booking> bookings = bookingService.getBookingsForClient(user.getUserId());
                System.out.println("\nBookings:");
                for (Booking b : bookings) {
                    System.out.println("Booking #" + b.getBookingId() + " | " + b.getBookingTime() + " | " + b.getStatus());
                }

            } else if (choice == 2) {
                System.out.print("Enter Booking ID: ");
                int bookingId = sc.nextInt();

                boolean ok = bookingService.completeBooking(bookingId);
                if (ok) System.out.println("✅ Booking marked as completed.");
                else System.out.println(" Failed to update booking.");

            } else if (choice == 3) {
                System.out.println("Logging out...");
                break;
            }
        }
    }
}
