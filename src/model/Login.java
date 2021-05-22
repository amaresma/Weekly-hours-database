/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import main.WeeklyHoursDatabaseException;
import main.LoginComponent;

/**
 *
 * @author Albert
 */
public class Login implements LoginComponent {
    
    private String name;
    private String password;
    private Map<String, String> login = new HashMap();
    
    /**
     * Constructor that creates a User
     * @param name
     * @param password
     */
    public Login(String name, String password) {
        this.name = name;
        this.password = password;
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
    
    public Map<String, String> getLogin() {
        return login;
    }
    
    public void setLogin (String name, String password) {
        this.login.put(name, password);
    }
    
    public void addLogin() {
        System.out.println("\nUsername: ");
        String nameSet = DATA.nextLine();
        System.out.println("\nPassword: ");
        String passwordSet = DATA.nextLine();
        setLogin(nameSet, passwordSet);
    }
    
    @Override
    public void updateUsername() throws WeeklyHoursDatabaseException {
        System.out.println("\nUsername: " + name);
        System.out.println("\nEnter the new UserName: ");
        for (int i = 0; i < login.size(); i++) {
            if (login.containsKey(name)) {
                login.replace(DATA.next(), password);
                DATA.nextLine();
            }
        }
    }

    @Override
    public void updatePassword() throws WeeklyHoursDatabaseException {
        System.out.println("\nUsername: " + name);
        System.out.println("\nEnter the new Password: ");
        login.replace(name, DATA.next());
        DATA.nextLine();
    }

    @Override
    public void showComponent() {
        for (int i = 0; i < login.size(); i++) {
            System.out.println(login.get(i));
        }
    }

    @Override
    public void deleteComponent() {
        System.out.println("\nUsername: ");
        login.remove(DATA.next());
        DATA.nextLine();
    }
    
}
