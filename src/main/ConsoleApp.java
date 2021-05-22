/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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

    public static void main(String args[]) {

    }

    public static String selectLogin() {
        System.out.println("\nUsername: ");
        String username = DATA.nextLine();
        System.out.println("\nPassword: ");
        String password = DATA.nextLine();
        if (username == "admin" && password == "123") {
            return username;

        } else {
            for (int i = 0; i < actualLogin.getLogin().size(); i++) {
                if (actualLogin.getLogin().containsKey(username) && actualLogin.getLogin().containsValue(password)) {
                    return username;
                }
            }
        }

        return null;
    }

}
