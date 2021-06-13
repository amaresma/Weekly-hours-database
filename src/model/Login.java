/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import main.Component;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class Login implements Component {

    private String name;
    private String password;
    private List<UserWorkSheet> userWorkSheetList = new ArrayList();

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
    
    public List<UserWorkSheet> getUserWorkSheet() {
        return userWorkSheetList;
    }
    
    public void setUserWorkSheet() {
        
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

    public static Login addLogin() {
        System.out.println("\nUsername: ");
        String nameSet = DATA.nextLine();
        System.out.println("\nPassword: ");
        String passwordSet = DATA.nextLine();
        return new Login(nameSet, passwordSet);
    }

    public void addUserWorkSheet(UserWorkSheet userWorkSheet) throws WeeklyHoursDatabaseException {
        if (userWorkSheet == null) {
            userWorkSheet = UserWorkSheet.addWorkSheet();    
        }
        // Select component conditions needs to be done
        userWorkSheetList.add(userWorkSheet);
        for (int i = 0; i < userWorkSheetList.size(); i++) {
            System.out.println("DAY: " + userWorkSheetList.get(i).getDay() 
                    + " MONTH: "+userWorkSheetList.get(i).getMonth()  
                    +" YEAR: " + userWorkSheetList.get(i).getYear()
                    +" HOURS: " + userWorkSheetList.get(i).getHours()
                    +" MINUTES: " + userWorkSheetList.get(i).getMinutes());
        }
        
    }

    public static void deleteComponent(String user) {
        for (int i = 0; i < ConsoleApp.getLogin().size(); i++) {
            if (ConsoleApp.getLogin().get(i).getName().equals(user)) {
                ConsoleApp.getLogin().remove(i);
            }
        }
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
