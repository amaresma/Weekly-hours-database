/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import login.Admin;
import login.User;
import persistance.DB4OManager;
import persistance.SERIALManager;

/**
 *
 * @author Albert
 */
public class ConsoleApp {

    private final static Scanner DATA = new Scanner(System.in);
    private static User user;
    private static SERIALManager serialManager = new SERIALManager();
    private static DB4OManager db4oManager = new DB4OManager();
    private static String serialDatabase = "admin.ser";
    private static String db4oDatabase = "users.db4o";
    private static Admin admin;
    private static List<User> loginList = new ArrayList();

    public static Admin getAdmin() {
        return admin;
    }
    
    public static void setAdmin(Admin addAdmin) {
        admin = addAdmin;
    }
    
    public String getDB4O() {
        return db4oDatabase;
    }

    public static List<User> getList() {
        return loginList;
    }

    public User getUser() {
        return user;
    }

    public static void setUser(User user) {
        loginList.add(user);
    }

    public static void main(String args[]) throws WeeklyHoursDatabaseException {
        int option = 0;
        do {
            try {
                serialManager.load(serialDatabase);
                db4oManager.load(db4oDatabase); // LOAD USERS
                System.out.println("\nSelect and option: ");
                System.out.println("\n0. Exit");
                System.out.println("\n1. Login");
                option = DATA.nextInt();
                switch (option) {
                    case 0:
                        break;
                    case 1:
                        loginMenu();
                        break;
                    default:
                        System.out.println("You need to select a correct option from the menu.");
                        break;
                }
            } catch (InputMismatchException e) {
                throw new WeeklyHoursDatabaseException("1");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (option != 0);

    }

    public static void loginMenu() throws WeeklyHoursDatabaseException, InputMismatchException {
        try {
            String name = selectLogin(1);
            if (null == name) {
                System.out.println("Wrong user");
            } else {
                switch (name) {
                    case "admin":

                        adminMenu();
                        break;
                    default:
                        for (int i = 0; i < loginList.size(); i++) {
                            if (loginList.get(i).getName().equals(name)) {
                                user = loginList.get(i);
                            }
                        }
                        userMenu();
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR LOGIN MENU");
            throw new WeeklyHoursDatabaseException("1");
        }

    }

    private static void adminMenu() throws WeeklyHoursDatabaseException {
        int option = 0;
        do {
            System.out.println("\nSelect and option: ");
            System.out.println("\n0. Exit");
            System.out.println("\n1. Add user");
            System.out.println("\n2. Update username");
            System.out.println("\n3. Update password");
            System.out.println("\n4. Delete user");
            System.out.println("\n5. Show users");
            System.out.println("\n6. Update admin password");
            option = DATA.nextInt();

            switch (option) {
                case 0:
                    break;
                case 1: // ADD USER
                    db4oManager.save(db4oDatabase, null, 1);
                    break;
                case 2: // UPDATE USERNAME
                    String userU = selectLogin(2);
                    db4oManager.save(db4oDatabase, userU, 2);
                    break;
                case 3: // UPDATE PASSWORD
                    String userP = selectLogin(2);
                    db4oManager.save(db4oDatabase, userP, 3);
                    break;
                case 4: // DELETE USERNAME
                    String userD = selectLogin(2);
                    db4oManager.save(db4oDatabase, userD, 4);
                    break;
                case 5: // SHOW USERS
                    for (int i = 0; i < loginList.size(); i++) {
                        loginList.get(i).showComponent(1);
                    }
                    break;
                case 6: // ADMIN PASSWORD
                    serialManager.save(serialDatabase, admin.getPassword(), 1);
                    break;
                default:
                    System.out.println("ADMIN MENU ERROR");
            }
        } while (option != 0);
    }

    private static void userMenu() throws WeeklyHoursDatabaseException {
        int option = 0;
        do {
            System.out.println("\nSelect and option: ");
            System.out.println("\n0. Exit");
            System.out.println("\n1. Add WorkDay");
            System.out.println("\n2. Update WorkDay");
            System.out.println("\n3. Delete WorkDay");
            System.out.println("\n4. Show WorkDay");
            option = DATA.nextInt();

            switch (option) {
                case 0:
                    break;
                case 1:
                    //add workday;
                    user.addWork(null);
                    break;
                case 2:
                    //update workday

                    break;
                case 3:
                    //delete workday
                    break;
                case 4:
                    //show Workday
                    for (int i = 0; i < user.getWork().size(); i++) {
                        System.out.println("YEAR: " + user.getWork().get(i).getYear());
                        System.out.println("MONTH: " + user.getWork().get(i).getMonth());
                        System.out.println("DAY: " + user.getWork().get(i).getDay());
                        System.out.println("MINUTES: " + user.getWork().get(i).getNormalMinutes());
                    }
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    public static String selectLogin(int option) {
        String item = null;
        switch (option) {
            case 1:
                System.out.println("\nUsername: ");
                String username = DATA.next();
                DATA.nextLine();
                System.out.println("\nPassword: ");
                String password = DATA.next();
                DATA.nextLine();
                
                if (username.equals("admin") && password.equals(admin.getPassword())) {
                    return username;
                } else {
                    try {
                        for (int i = 0; i < loginList.size(); i++) {
                            if (loginList.get(i).getName().equals(username) && loginList.get(i).getPassword().equals(password)) {
                                return username;
                            }
                        }
                    } catch (NullPointerException e) {
                        System.out.println("User doesn't exist");
                    }
                }
                break;
            case 2:
                System.out.println("Select user: ");
                item = DATA.next();
                break;
        }
        return item;
    }

}
