/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import java.time.LocalDate;
import java.util.Calendar;
import login.Login;
import main.Component;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class UserWorkSheet implements Component {
    
    Login login;
    private int date, month, year;
    private int hours, minutes, normalH, extraH;
    private boolean saturday = false, sunday = false, bankHoliday = false;
    private static String keyboard;
    
    private static Calendar c = new Calendar() {
        @Override
        protected void computeTime() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected void computeFields() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(int field, int amount) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void roll(int field, boolean up) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getMinimum(int field) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getMaximum(int field) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getGreatestMinimum(int field) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getLeastMaximum(int field) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public UserWorkSheet(int date, int month, int year, int hours, int minutes,
            int normalH, int extraH, boolean saturday, boolean sunday,
            boolean bankHoliday) throws WeeklyHoursDatabaseException {
        this.date = date;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
        this.normalH = normalH;
        this.extraH = extraH;
        this.saturday = saturday;
        this.sunday = sunday;
        this.bankHoliday = bankHoliday;
    }

    public int getDay() {
        return date;
    }

    public void setDay(int date) {
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
    
    public int getNormalH() {
        return normalH;
    }
    
    public int getExtraH() {
        return extraH;
    }
    
    public boolean getSaturday() {
        return saturday;
    }
    
    public boolean getSunday() {
        return sunday;
    }
    
    public boolean getBankHoliday() {
        return bankHoliday;
    }

    public static UserWorkSheet addWorkSheet() throws WeeklyHoursDatabaseException {
        int date = 0, month = 0, year = 0, hours = 0, minutes = 0, extra = 0, normal = 0;
        boolean saturday = false, sunday = false, bankHoliday = false;

        try {
            System.out.println("\nEnter day: ");
            date = DATA.nextInt();
            System.out.println("\nEnter month: ");
            month = DATA.nextInt();
            System.out.println("\nEnter year: ");
            year = DATA.nextInt();
            c.set(year, month, date);
            System.out.println("\nEnter hours: ");
            hours = DATA.nextInt();
            System.out.println("\nEnter minutes: ");
            minutes = DATA.nextInt();
            System.out.println("\nBank Holiday (y/n)?");
            keyboard = DATA.next();
            DATA.nextLine();
            if (keyboard.equals("y")) {
                bankHoliday = true;
            } else if (!keyboard.equals("n") && !keyboard.equals("y")) {
                throw new WeeklyHoursDatabaseException("BankHoliday error");
            }
            
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
        if ("SATURDAY".equals(localDate.getDayOfWeek().toString())) {
            saturday = true;
        } else if ("SUNDAY".equals(localDate.getDayOfWeek().toString())) {
            sunday = true;
        }
        return new UserWorkSheet(date, month, year, hours, minutes, extra, normal, saturday, sunday, bankHoliday);
    }

    @Override
    public void updateComponent(int option) {
        
    }

    @Override
    public void showComponent(int option) {
        
    }

}
