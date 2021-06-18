/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import static main.Component.DATA;
import persistance.XMLManager;

/**
 *
 * @author Albert
 */
public class Admin extends Login {
    
    private static XMLManager xmlManager = new XMLManager();
    private static String xmlDatabase = "";
    
    private Admin(String admin, String password) {
        super.name = admin;
        super.password = password;
    }
    
    public static Admin addAdmin() {
        return new Admin("admin", "123");
    }

    public static User addUser() {
        System.out.println("\nUsername: ");
        String nameSet = DATA.nextLine();
        System.out.println("\nPassword: ");
        String passwordSet = DATA.nextLine();
        return new User(nameSet, passwordSet);
    }

    

}
