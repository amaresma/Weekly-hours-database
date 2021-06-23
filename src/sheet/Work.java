/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import java.time.LocalDate;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class Work extends Sheet {

    private int normalHours, normalMinutes, extraHours, extraMinutes;

    public Work(int day, int month, int year, int normalHours,
            int normalMinutes, int extraHours, int extraMinutes) {
        super(day, month, year);
        this.normalHours = normalHours;
        this.normalMinutes = normalMinutes;
        this.extraHours = extraHours;
        this.extraMinutes = extraMinutes;
    }
    
    public Work(int month, int year) {
        super(month, year);
    }

    /**
     *
     * @param month
     * @param year
     */

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getYear() {
        return year;
    }

    public int getNormalHours() {
        return normalHours;
    }

    public void setNormalHours(int normalHours) {
        this.normalHours = normalHours;
    }

    public int getNormalMinutes() {
        return normalMinutes;
    }

    public void setNormalMinutes(int normalMinutes) {
        this.normalMinutes = normalMinutes;
    }

    public int getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(int extraHours) {
        this.extraHours = extraHours;
    }

    public int getExtraMinutes() {
        return extraMinutes;
    }

    public void setExtraMinutes(int extraMinutes) {
        this.extraMinutes = extraMinutes;
    }

    public static Work addWork() throws WeeklyHoursDatabaseException {
        int day = 0, month = 0, year = 0, hours = 0, minutes = 0,
                normalHours = 0, normalMinutes = 0, extraHours = 0,
                extraMinutes = 0;
        boolean extra = false;

        try {
            System.out.println("\nEnter day: ");
            day = DATA.nextInt();
            System.out.println("\nEnter month: ");
            month = DATA.nextInt();
            System.out.println("\nEnter year: ");
            year = DATA.nextInt();
            c.set(year, month, day);
            System.out.println("\nEnter hours: ");
            hours = DATA.nextInt();
            System.out.println("\nEnter minutes: ");
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

        if (minutes >= 30) { // Minutes set
            minutes = 30;
        } else {
            minutes = 0;
        }

        LocalDate localDate = LocalDate.of(year, month, day);
        if ("SATURDAY".equals(localDate.getDayOfWeek().toString())) {
            extra = true;
        } else if ("SUNDAY".equals(localDate.getDayOfWeek().toString())) {
            extra = true;
        }

        if (extra) {
            extraHours = hours;
            extraMinutes = minutes;
        } else {
            if (hours > 8) {
                normalHours = 8;
                extraHours = hours - normalHours;
                extraMinutes = minutes;
            } else {
                normalHours = hours;
                normalMinutes = minutes;
            }
        }

        return new Work(day, month, year, normalHours, normalMinutes,
                extraHours, extraMinutes);
    }

    public static Work selectWork() {
        int month = 0, year = 0;
        System.out.println(" \nEnter month: ");
        month = DATA.nextInt();
        System.out.println("\nEnter year: ");
        year = DATA.nextInt();
        return new Work(month, year);
    }

    @Override
    public void showComponent(int option) {

    }

    @Override
    public void updateComponent(int option) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
