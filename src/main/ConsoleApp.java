/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Login;
import persistance.PersistanceManager;

/**
 *
 * @author Albert
 */
public class ConsoleApp {

    private final static Scanner DATA = new Scanner(System.in);
    private static Login actualLogin;
    static private PersistanceManager pm = new PersistanceManager();

    public static void main(String args[]) throws WeeklyHoursDatabaseException {
        int option = 0;
        do {
            try {
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
            }
        } while (option != 0);

    }
    
    public static void loginMenu() throws WeeklyHoursDatabaseException, InputMismatchException {
        String name = selectLogin();
        if (null == name) {
            System.out.println("Wrong user");
        } else switch (name) {
            case "admin":
                adminMenu();
                break;
            default:
                break;
        }
        
    }
    
    private static void adminMenu() {
        System.out.println("HOLI");
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
                for (int i = 0; i < actualLogin.getLogin().size(); i++) {
                if (actualLogin.getLogin().containsKey(username) && actualLogin.getLogin().containsValue(password)) {
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
