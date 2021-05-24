/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import java.util.Map;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;
import model.Login;

/**
 *
 * @author Albert
 */
public class DB4OManager implements PersistanceProviderLogin {

    private ObjectContainer db;
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void startConnection() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Login.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile("database.db4o");
    }

    public void stopConnection() {
        db.close();
    }

    @Override
    public void saveDB4O(String database, Login login) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            db.store(login);
        } catch (Exception e) {
            System.out.println("ERROR DB4O SAVE");
        } finally {
            stopConnection();
        }

    }

    @Override
    public void loadDB40(String database) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            Login test = new Login();
            ObjectSet<Login> result = db.queryByExample(test);
            while(result.hasNext()) {
                Login input = result.next();
                ConsoleApp.setLogin(input);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR DB4O LOAD");
        } finally {
            stopConnection();
        }
    }

}
