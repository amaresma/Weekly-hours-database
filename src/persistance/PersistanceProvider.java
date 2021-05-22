/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import main.WeeklyHoursDatabaseException;
import model.Day;

/**
 *
 * @author Albert
 */
public interface PersistanceProvider {
    public void saveDay (String user, Day day) throws WeeklyHoursDatabaseException;
    public void loadDay (String user) throws WeeklyHoursDatabaseException;
}
