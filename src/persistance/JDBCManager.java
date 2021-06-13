/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import main.WeeklyHoursDatabaseException;
import model.Login;
import model.UserWorkSheet;

/**
 *
 * @author Albert
 */
public class JDBCManager implements PersistanceProvider {

    @Override
    public void save(String database, String item, Login login, int option) throws WeeklyHoursDatabaseException {
        switch (option) {
            case 1: // ADD
                break;
            case 2: // UPDATE
                break;
            case 3: // DELETE
                break;
        }
    }

    @Override
    public void load(String database) throws WeeklyHoursDatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
