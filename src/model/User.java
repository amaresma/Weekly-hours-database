/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import main.UserComponent;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class User implements UserComponent{
    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes = 0;
    private Map<Login, Date> user = new HashMap();
    
    public User(String user, Date date) {
        
    }

    @Override
    public void updateComponent() throws WeeklyHoursDatabaseException {
        
    }

    @Override
    public void showComponent() {
        
    }

    @Override
    public void deleteComponent() throws WeeklyHoursDatabaseException {
        
    }
    
}
