/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;
import main.LoginComponent;

/**
 *
 * @author Albert
 */
public class Login implements LoginComponent {
    
    private String name;
    private String password;
    private ConsoleApp app;
    //List<Login> loginList = new ArrayList();
    
    /**
     * Constructor that creates a User
     * @param name
     * @param password
     */
    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Login() {
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /*
    public List<Login> getLogin() {
        return loginList;
    }
    
    public void setLogin (Login login) {
        loginList.add(login);
    }
    */
    public static Login addLogin() {
        System.out.println("\nUsername: ");
        String nameSet = DATA.nextLine();
        System.out.println("\nPassword: ");
        String passwordSet = DATA.nextLine();
        return new Login(nameSet, passwordSet);
    }
    
    @Override
    public void updateUsername() throws WeeklyHoursDatabaseException {
        System.out.println("\nUsername: " + name);
        System.out.println("\nEnter the new UserName: ");
        name = DATA.next();
    }

    @Override
    public void updatePassword() throws WeeklyHoursDatabaseException {
        System.out.println("\nUsername: " + name);
        System.out.println("\nEnter the new Password: ");
        password = DATA.next();
    }

    @Override
    public void showComponent() {
        System.out.println("\nUsername: " + name + "\nPassowrd: " + password);
    }

    @Override
    public void deleteComponent() {
        System.out.println("\nUsername: ");
        String username = DATA.next();
        
    }
    
}
