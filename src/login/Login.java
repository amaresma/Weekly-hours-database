/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import main.Component;
import main.ConsoleApp;

/**
 *
 * @author Albert
 */
public abstract class Login implements Component {
    
    protected String name;
    protected String password;
    //protected List<Work> workList = new ArrayList();

    /**
     * Constructor that creates a User
     *
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

    @Override
    public void showComponent(int option) {
        switch (option) {
            case 1:
                System.out.println("\nUsername: " + name + "\nPassowrd: " + password);
                break;
        }

    }

    @Override
    public void updateComponent(int option) {
        switch (option) {
            case 1:
                System.out.println("\nEnter the new UserName: ");
                name = DATA.next();
                break;
            case 2:
                System.out.println("\nEnter the new Password: ");
                password = DATA.next();
                break;
        }
    }

}
