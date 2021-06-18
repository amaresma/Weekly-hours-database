/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import login.Admin;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class SERIALManager implements PersistanceProvider {

    ConsoleApp app;
    Admin admin;

    @Override
    public void save(String database, String item, int option) throws WeeklyHoursDatabaseException {

    }

    @Override
    public void load(String database) throws WeeklyHoursDatabaseException {

        try {
            File adminFile = new File(database);
            adminFile.createNewFile();
            if (adminFile.list() == null) {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(adminFile));
                admin = Admin.addAdmin();
                //app.setAdmin(admin);
                oos.writeObject(admin);
            }

        } catch (IOException ex) {
            System.out.println("HELLO");
            throw new WeeklyHoursDatabaseException("charge");
        } //catch (ClassNotFoundException ex) {
            //System.out.println("HOLA");
            //throw new WeeklyHoursDatabaseException("class");
        //}

    }

}
