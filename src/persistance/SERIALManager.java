/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.Admin;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class SERIALManager implements PersistanceProvider {

    private ConsoleApp app;
    private Admin admin;

    @Override
    public void save(String database, String item, int option) throws WeeklyHoursDatabaseException {
        switch (option) {
            case 1:
                admin.updateComponent(2);
                break;
        }
        File adminFile = new File(database);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(adminFile));
            oos.writeObject(admin);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR1");
        } catch (IOException ex) {
            System.out.println("ERROR2");
        }
    }

    @Override
    public void load(String database, String item, int option) throws WeeklyHoursDatabaseException {
        
        ObjectOutputStream oos;
        ObjectInputStream ois;
        try {
            File adminFile = new File(database);
            if (!adminFile.exists()) {
                admin = Admin.addAdmin();
                oos = new ObjectOutputStream(new FileOutputStream(adminFile));
                oos.writeObject(admin);
                oos.close();
            }
            ois = new ObjectInputStream(new FileInputStream(adminFile));
            admin = (Admin) ois.readObject();
            ConsoleApp.setAdmin(admin);
            ois.close();  

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SERIALManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SERIALManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SERIALManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
