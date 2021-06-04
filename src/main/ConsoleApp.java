/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Login;
import persistance.DB4OManager;
import persistance.JDBCManager;

/**
 *
 * @author Albert
 */
public class ConsoleApp {

    private final static Scanner DATA = new Scanner(System.in);
    private static Login actualLogin;
    private static Login login;
    static private DB4OManager db4oManager = new DB4OManager();
    static private JDBCManager jdbcManager = new JDBCManager();
    static private String db4oDatabase = "database.db4o";
    static private String jdbcDatabase = "";
    private static List<Login> loginList = new ArrayList();

    public String getDB4O() {
        return db4oDatabase;
    }

    public static void setLogin(Login login) {
        loginList.add(login);
    }

    public static void main(String args[]) throws WeeklyHoursDatabaseException {
        int option = 0;
        do {
            try {
                db4oManager.loadDB40(db4oDatabase);
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
            String name = selectLogin();
            if (null == name) {
                System.out.println("Wrong user");
            } else {
                switch (name) {
                    case "admin":

                        adminMenu();
                        break;
                    default:
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
                case 1:
                    boolean exists = false;
                    login = login.addLogin();
                    if (loginList.isEmpty()) {
                        loginList.add(login);
                        db4oManager.saveDB4O(db4oDatabase, login);
                    } else {
                        for (int i = 0; i < loginList.size(); i++) {
                            if (loginList.get(i).getName().equals(login.getName())) {
                                System.out.println("User already exists");
                                exists = true;
                            }
                        }
                        if (!exists) {
                            loginList.add(login);
                                db4oManager.saveDB4O(db4oDatabase, login);
                        }
                    }

                    //login.setLogin(login.getName(), login.getPassword());
                    //db4oManager.saveDB4O(db4oDatabase, actualLogin.getLogin()., actualLogin);
                    break;
                case 2:
                    login.updateUsername();
                    if (true) {

                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    for (int i = 0; i < loginList.size(); i++) {
                        loginList.get(i).showComponent();
                    }
                    break;
                default:
                    System.out.println("ADMIN MENU ERROR");
            }
        } while (option != 0);
    }

    private static void userMenu() {
        System.out.println("HOLI");
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
                    break;
                case 2:
                    //update workday
                    break;
                case 3:
                    //delete workday
                    break;
                case 4:
                    //show Workday
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    public static String selectLogin() {
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
        return null;
    }

}
