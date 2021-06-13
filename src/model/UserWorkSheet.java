/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import main.Component;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class UserWorkSheet implements Component{
    private int date, month, year;
    private int hours, minutes, normalH, extraH;
    private boolean saturday = false, sunday = false, bankHoliday = false;
    String keyboard;
    private static Calendar c;
    
    
    public UserWorkSheet(int date, int month, int year, int hours, int minutes, 
            int normal, int extra, boolean saturday, boolean sunday, 
            boolean bankHoliday) throws WeeklyHoursDatabaseException {
        this.date = date;
    }
    
    public int getDay() {
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
        int date = 0, month = 0, year = 0, hours = 0, minutes = 0, extra = 0, normal = 0;
        boolean saturday = false, sunday = false, bankHoliday = false;
        
        try {
            System.out.println("Enter day: ");
            date = DATA.nextInt();
            System.out.println("Enter month: ");
            month = DATA.nextInt();
            System.out.println("Enter year: ");
            year = DATA.nextInt();
            c.set(year, month, date);
            System.out.println("Enter hours: ");
            hours = DATA.nextInt();
            minutes = DATA.nextInt();
            if (hours <= 0 || hours > 24) {
                throw new WeeklyHoursDatabaseException("Error hours");
            }
            if (minutes < 0 || minutes > 59) {
                throw new WeeklyHoursDatabaseException("Error minutes");
            }
            if (hours == 24 && minutes > 0) {
                throw new WeeklyHoursDatabaseException("Time excedded");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        if (minutes >= 30) {
            minutes = 30;
        } else {
            minutes = 0;
        }
        
        if (hours > 8) {
            normal = 8;
            extra = hours - normal;
        } else {
            normal = hours;
        }

        LocalDate localDate = LocalDate.of(year, month, date);
        System.out.println("DAY: " + localDate.getDayOfWeek());
        if ("SATURDAY".equals(localDate.getDayOfWeek().toString())) {
            saturday = true;
        } else if ("SUNDAY".equals(localDate.getDayOfWeek().toString())) {
            sunday = true;
        }
        return new UserWorkSheet(date, month, year, hours, minutes, extra, normal, saturday, sunday, bankHoliday);
    }

    @Override
    public void updateComponent(int option) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showComponent(int option) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
