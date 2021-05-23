/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import main.WeeklyHoursDatabaseException;
import model.Login;

/**
 *
 * @author Albert
 */
public class DB4OManager implements PersistanceProviderLogin {
    //private ObjectContainer db;


    @Override
    public void saveDB4O(String database, String username, Login login) throws WeeklyHoursDatabaseException {
        
    }

    @Override
    public void loadDB40(String database) throws WeeklyHoursDatabaseException {
        System.out.println("HELLO");
    }

    
}
