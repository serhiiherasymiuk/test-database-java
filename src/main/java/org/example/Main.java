package org.example;

import org.example.dao.UserDao;
import org.example.entities.User;
import org.example.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSesseionFactory();
        try (Scanner in = new Scanner(System.in)) {
            UserDao userDao = new UserDao(sf);

            while (true) {
                System.out.println("Enter an option (1: Get by id, 2: Get all users, 3: Update, 4: Add, 5: Delete q: Quit): ");
                String command = in.nextLine().trim().toLowerCase();

                switch (command) {
                    case "1":
                        System.out.println("Enter user ID to get: ");
                        int id = Integer.parseInt(in.nextLine());
                        Optional<User> retrievedUserOptional = userDao.get(id);
                        if (retrievedUserOptional.isPresent()) {
                            User retrievedUser = retrievedUserOptional.get();
                            System.out.println("Retrieved User: " + retrievedUser);
                        } else {
                            System.out.println("User not found");
                        }
                        break;
                    case "2":
                        List<User> allUsers = userDao.getAll();
                        System.out.println("All Users:");
                        for (User user : allUsers) {
                            System.out.println(user);
                        }
                        break;
                    case "3":
                        System.out.println("Enter user ID to update: ");
                        int updateUserId = Integer.parseInt(in.nextLine());

                        Optional<User> updateUserOptional = userDao.get(updateUserId);
                        if (updateUserOptional.isPresent()) {
                            User updateUser = updateUserOptional.get();

                            System.out.print("Enter updated email: ");
                            updateUser.setEmail(in.nextLine());

                            System.out.print("Enter updated first name: ");
                            updateUser.setFirstName(in.nextLine());

                            System.out.print("Enter updated last name: ");
                            updateUser.setLastName(in.nextLine());

                            System.out.print("Enter updated phone: ");
                            updateUser.setPhone(in.nextLine());

                            System.out.print("Enter updated password: ");
                            updateUser.setPassword(in.nextLine());

                            userDao.update(updateUser);

                            System.out.println("User updated successfully: " + updateUser);
                        } else {
                            System.out.println("User not found");
                        }
                        break;
                    case "4":
                        User user = new User();

                        System.out.print("Enter email: ");
                        user.setEmail(in.nextLine());

                        System.out.print("Enter first name: ");
                        user.setFirstName(in.nextLine());

                        System.out.print("Enter last name: ");
                        user.setLastName(in.nextLine());

                        System.out.print("Enter phone: ");
                        user.setPhone(in.nextLine());

                        System.out.print("Enter password: ");
                        user.setPassword(in.nextLine());

                        userDao.save(user);

                        System.out.println("User created successfully: " + user);
                        break;
                    case "5":
                        System.out.println("Enter user ID to delete: ");
                        int deleteUserId = Integer.parseInt(in.nextLine());

                        Optional<User> deleteUserOptional = userDao.get(deleteUserId);
                        if (deleteUserOptional.isPresent()) {
                            User deleteUser = deleteUserOptional.get();

                            userDao.delete(deleteUser);

                            System.out.println("User deleted successfully: " + deleteUser);
                        } else {
                            System.out.println("User not found");
                        }
                        break;
                    case "q":
                        System.out.println("Exiting");
                        sf.close();
                        System.exit(0);
                    default:
                        System.out.println("Wrong command");
                        break;
                }
            }
        }
    }
}
