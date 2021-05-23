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

    

    @Override
    public void saveJDBC(String database, String username, UserWorkSheet userData) throws WeeklyHoursDatabaseException {
        
    }

    @Override
    public void loadJDBC(String database, String username) throws WeeklyHoursDatabaseException {
        
    }
    
}
