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
public interface PersistanceProviderLogin {
    public void saveDB4O (String database, Login login) throws WeeklyHoursDatabaseException;
    public void loadDB40 (String database) throws WeeklyHoursDatabaseException;
}
