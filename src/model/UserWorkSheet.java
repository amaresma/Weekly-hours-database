/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import main.UserComponent;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class UserWorkSheet implements UserComponent{
    private int date;
    private int month;
    private int year;
    private int hours;
    private int minutes = 0;
    String workedTime;
    private Map<Calendar, String> user = new HashMap();
    Calendar c = Calendar.getInstance();
    
    
    public UserWorkSheet(int date, int month, int year, int hours, int minutes) throws WeeklyHoursDatabaseException {
        this.c.set(year, month, date);
        this.workedTime = String.valueOf(hours) + ":" + String.valueOf(minutes);
    }
    
    public int getDate() {
        return date;
    }
    
    public void setDate(int date) {
        this.date = date;
    }
    
    public int getMonth() {
        return month;
    }
    
    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public int getHours() {
        return hours;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
    public static UserWorkSheet addWorkSheet() throws WeeklyHoursDatabaseException {
        int date = 0, month = 0, year = 0, hours = 0, minutes = 0;
        return new UserWorkSheet(date, month, year, hours, minutes);
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
