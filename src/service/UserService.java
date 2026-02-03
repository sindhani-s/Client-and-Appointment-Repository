package service;

import dao.UserDao;
import model.User;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // Register a new user
    public boolean registerUser(String fullName, String email, String password, String role) {
        try {
            // to Check if the email already exists
            User existing = userDao.findByEmail(email);
            if (existing != null) {
                System.out.println("Email already registered.");
                return false;
            }

            // Create new user
            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPasswordHash(password);
            user.setRole(role);

            userDao.createUser(user);
            return true;

        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    // Login user
    public User login(String email, String password) {
        try {
            User user = userDao.findByEmail(email);

            if (user == null) {
                System.out.println("User not found.");
                return null;
            }

            if (!user.getPasswordHash().equals(password)) {
                System.out.println("Incorrect password.");
                return null;
            }

            return user;

        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        }
    }
}
