/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sheet;

import sheet.UserWorkSheet;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class Holiday extends UserWorkSheet{
    
    public Holiday(int date, int month, int year, int hours, int minutes, int normalH, int extraH, boolean saturday, boolean sunday, boolean bankHoliday) throws WeeklyHoursDatabaseException {
        super(date, month, year, hours, minutes, normalH, extraH, saturday, sunday, bankHoliday);
    }
    
}
