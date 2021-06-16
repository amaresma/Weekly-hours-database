/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import static main.Component.DATA;

/**
 *
 * @author Albert
 */
public class Admin extends Login {
    

    public static User addUser() {
        System.out.println("\nUsername: ");
        String nameSet = DATA.nextLine();
        System.out.println("\nPassword: ");
        String passwordSet = DATA.nextLine();
        return new User(nameSet, passwordSet);
    }

}
