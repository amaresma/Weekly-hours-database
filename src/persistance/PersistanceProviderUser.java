/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import main.WeeklyHoursDatabaseException;
import model.UserWorkSheet;

/**
 *
 * @author Albert
 */
public interface PersistanceProviderUser {
    public void saveJDBC (String database, String username, UserWorkSheet userData) throws WeeklyHoursDatabaseException;
    public void loadJDBC (String database, String username) throws WeeklyHoursDatabaseException;
}
