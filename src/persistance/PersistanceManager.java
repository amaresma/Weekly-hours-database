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
public class PersistanceManager {
    private PersistanceProviderLogin loginManager;
    private PersistanceProviderUser userManager;
    
    public void save (String persistance, String fileName, Login user) throws WeeklyHoursDatabaseException {
        switch (persistance) {
            case "login":
                loginManager = new DB4OManager();
                break;
            case "user":
                userManager = new JDBCManager();
                break;
        }
    }
}
