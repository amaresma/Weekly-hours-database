/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public interface PersistanceProviderLogin {
    public void save (String user, String password) throws WeeklyHoursDatabaseException;
    public void load (String user, String password) throws WeeklyHoursDatabaseException;
}
