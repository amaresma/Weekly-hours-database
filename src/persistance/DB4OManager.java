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
    private ConsoleApp app;

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
            ObjectSet<Login> result = db.queryByExample(login.getLogin());
            if (result.isEmpty()) {
                db.store(login);
            } else {
                if (result.hasNext()) {
                    Login loginQuery = result.next();
                    loginQuery = login;
                    db.store(loginQuery);
                }
            }
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
            ObjectSet<Login> result = db.queryByExample(login);
            if (result.isEmpty()) {
                throw new WeeklyHoursDatabaseException("Database doesn't exist");
            } else {
                while (result.hasNext()) {
                    Login loginQuery = result.next();
                    login = loginQuery;
                }
            }

        } catch (WeeklyHoursDatabaseException e) {
            System.out.println("ERROR DB4O EMPTY");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR DB4O LOAD");
        } finally {
            stopConnection();
        }
    }

}
