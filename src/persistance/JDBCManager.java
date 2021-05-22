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
public class JDBCManager implements PersistanceProviderUser {
    // SAVE THE DATA IN A JDBC

    @Override
    public void save(String user, UserWorkSheet userData) throws WeeklyHoursDatabaseException {
    }

    @Override
    public void load(String user) throws WeeklyHoursDatabaseException {
    }
    
}
