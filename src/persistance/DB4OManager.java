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
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;
import login.Login;
import login.User;

/**
 *
 * @author Albert
 */
public class DB4OManager implements PersistanceProvider {

    private ObjectContainer db;
    private User user;

    public Login getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void startConnection() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(User.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile("database.db4o");
    }

    public void stopConnection() {
        db.close();
    }

    @Override
    public void save(String database, String item, Login pLogin, int option) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            List<User> users = db.query(new Predicate<User>() {
                @Override
                public boolean match(User userQ) {
                    return userQ.getName().equals(item);
                }
            });
            switch (option) {
                case 1: // ADD User
                    if (users.isEmpty()) {
                        db.store(pLogin);
                        db.commit();
                    } else {
                        System.out.println("ERROR");
                    }

                    break;
                case 2: // UPDATE Username
                    if (!users.isEmpty()) {
                        user = users.iterator().next();
                        user.setName(pLogin.getName());
                        user.setPassword(pLogin.getPassword());
                        db.store(user);
                        db.commit();
                    } else {
                        System.out.println("ERROR!");
                    }
                    break;
                case 3: // UPDATE Password
                    if (!users.isEmpty()) {
                        user = users.iterator().next();
                        //login.setName(pLogin.getName());
                        user.setPassword(pLogin.getPassword());
                        db.store(user);
                        db.commit();
                    } else {
                        System.out.println("ERROR!!");
                    }
                    break;
                case 4: // DELETE
                    if (!users.isEmpty()) {
                        user = users.iterator().next();
                        db.delete(user);
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
    public void load(String database) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            User test = new User();
            ObjectSet<User> result = db.queryByExample(test);
            while (result.hasNext()) {
                User input = result.next();
                ConsoleApp.setUser(input);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR DB4O LOAD");
        } finally {
            stopConnection();
        }
    }

}
