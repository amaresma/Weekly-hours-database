/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.util.ArrayList;
import java.util.List;
import main.WeeklyHoursDatabaseException;
import sheet.UserWorkSheet;

/**
 *
 * @author Albert
 */
public class User extends Login {

    private List<UserWorkSheet> userWorkSheetList = new ArrayList();

    public User(String name, String password) {
        super(name, password);
    }

    public User() {
    }

    public List<UserWorkSheet> getUserWorkSheet() {
        return userWorkSheetList;
    }

    public void setUserWorkSheet() {

    }

    public void addUserWorkSheet(UserWorkSheet userWorkSheet) throws WeeklyHoursDatabaseException {
        if (userWorkSheet == null) {
            userWorkSheet = UserWorkSheet.addWorkSheet();
        }
        // Select component conditions needs to be done
        userWorkSheetList.add(userWorkSheet);
        for (int i = 0; i < userWorkSheetList.size(); i++) {
            System.out.println("DAY: " + userWorkSheetList.get(i).getDay()
                    + " MONTH: " + userWorkSheetList.get(i).getMonth()
                    + " YEAR: " + userWorkSheetList.get(i).getYear()
                    + " HOURS: " + userWorkSheetList.get(i).getHours()
                    + " MINUTES: " + userWorkSheetList.get(i).getMinutes());
        }

    }

}
