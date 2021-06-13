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
import com.db4o.query.Predicate;
import java.util.List;
import java.util.Map;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;
import model.Login;

/**
 *
 * @author Albert
 */
public class DB4OManager implements PersistanceProvider {

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
    public void save  (String database, String item, Login pLogin, int option) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            List<Login> logins = db.query(new Predicate<Login>() {
                @Override
                public boolean match(Login loginQ) {
                    return loginQ.getName().equals(item);
                }
            });
            switch (option) {
                case 1: // ADD User
                    if (logins.isEmpty()) {
                        db.store(pLogin);
                        db.commit();
                    } else {
                        System.out.println("ERROR");
                    }

                    break;
                case 2: // UPDATE Username
                    if (!logins.isEmpty()) {
                        login = logins.iterator().next();
                        login.setName(pLogin.getName());
                        login.setPassword(pLogin.getPassword());
                        db.store(login);
                        db.commit();
                    } else {
                        System.out.println("ERROR!");
                    }
                    break;
                case 3: // UPDATE Password
                    if (!logins.isEmpty()) {
                        login = logins.iterator().next();
                        //login.setName(pLogin.getName());
                        login.setPassword(pLogin.getPassword());
                        db.store(login);
                        db.commit();
                    } else {
                        System.out.println("ERROR!!");
                    }
                    break;
                case 4: // DELETE
                    if (!logins.isEmpty()) {
                        login = logins.iterator().next();
                        db.delete(login);
                        db.commit();
                    } else {
                        System.out.println("ERROR!!!");
                    }
            }

        } catch (Exception e) {
            System.out.println("ERROR DB4O SAVE");
        } finally {
            stopConnection();
        }

    }

    @Override
    public void load (String database) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            Login test = new Login();
            ObjectSet<Login> result = db.queryByExample(test);
            while (result.hasNext()) {
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
