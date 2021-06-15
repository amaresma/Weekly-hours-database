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
import persistance.JDBCManager;

/**
 *
 * @author Albert
 */
public class ConsoleApp {

    private final static Scanner DATA = new Scanner(System.in);
    private static User user;
    private static Admin admin;
    static private DB4OManager db4oManager = new DB4OManager();
    static private JDBCManager jdbcManager = new JDBCManager();
    static private String db4oDatabase = "database.db4o";
    static private String jdbcDatabase = "";
    private static List<User> loginList = new ArrayList();

    public String getDB4O() {
        return db4oDatabase;
    }

    public static List<User> getUser() {
        return loginList;
    }

    public static void setUser(User user) {
        loginList.add(user);
    }

    public static void main(String args[]) throws WeeklyHoursDatabaseException {
        int option = 0;
        do {
            try {
                db4oManager.load(db4oDatabase);
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
            option = DATA.nextInt();

            switch (option) {
                case 0:
                    break;
                case 1: // ADD USER
                    boolean exists = false;
                    user = admin.addUser();
                    if (loginList.isEmpty()) {
                        loginList.add(user);
                        db4oManager.save(db4oDatabase, user.getName(), user, 1);
                    } else {
                        for (int i = 0; i < loginList.size(); i++) {
                            if (loginList.get(i).getName().equals(user.getName())) {
                                System.out.println("User already exists");
                                exists = true;
                            }
                        }
                        if (!exists) {
                            loginList.add(user);
                            db4oManager.save(db4oDatabase, user.getName(), user, 1);
                        }
                    }
                    break;
                case 2: // UPDATE USERNAME
                    boolean save = false;
                    String userU = selectLogin(2);
                    for (int i = 0; i < loginList.size(); i++) {
                        if (loginList.get(i).getName().equals(userU)) {
                            loginList.get(i).updateComponent(1);
                            db4oManager.save(db4oDatabase, userU, loginList.get(i), 2);
                        }
                    }
                    break;
                case 3: // UPDATE PASSWORD
                    String userP = selectLogin(2);
                    for (int i = 0; i < loginList.size(); i++) {
                        if (loginList.get(i).getName().equals(userP)) {
                            loginList.get(i).updateComponent(2);
                            db4oManager.save(db4oDatabase, userP, loginList.get(i), 3);
                        }
                    }
                    break;
                case 4: // DELETE USERNAME
                    String userD = selectLogin(2);
                    for (int i = 0; i < loginList.size(); i++) {
                        if (loginList.get(i).getName().equals(userD)) {
                            db4oManager.save(db4oDatabase, userD, loginList.get(i), 4);
                            loginList.get(i).deleteComponent(userD);
                        }
                    }
                    break;
                case 5: // SHOW USERS
                    for (int i = 0; i < loginList.size(); i++) {
                        loginList.get(i).showComponent(1);
                    }
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
                    user.addUserWorkSheet(null);
                    break;
                case 2:
                    //update workday

                    break;
                case 3:
                    //delete workday
                    break;
                case 4:
                    //show Workday
                    for (int i = 0; i < user.getUserWorkSheet().size(); i++) {
                        System.out.println("YEAR: " + user.getUserWorkSheet().get(i).getYear());
                        System.out.println("MONTH: " + user.getUserWorkSheet().get(i).getMonth());
                        System.out.println("DAY: " + user.getUserWorkSheet().get(i).getDay());
                        System.out.println("MINUTES: " + user.getUserWorkSheet().get(i).getMinutes());
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
                if (username.equals("admin") && password.equals("123")) {
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
