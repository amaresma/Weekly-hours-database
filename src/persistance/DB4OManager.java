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
import login.Admin;
import main.ConsoleApp;
import main.WeeklyHoursDatabaseException;
import login.User;

/**
 *
 * @author Albert
 */
public class DB4OManager implements PersistanceProvider {

    private ObjectContainer db;
    private ConsoleApp app;
    private Admin admin;
    private User user;
    private User userQuery;

    public Admin getAdmin() {
        return admin;
    }

    public User getUser() {
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

    public void save(String database, String item, int option) throws WeeklyHoursDatabaseException {
        try {
            startConnection();
            List<User> users = db.query(new Predicate<User>() {
                @Override
                public boolean match(User userQ) {
                    return userQ.getName().equals(item);
                }
            });
            
            switch (option) {
                case 1:
                    boolean exists = false;
                    user = Admin.addUser();
                    if (ConsoleApp.getList().isEmpty()) {
                        ConsoleApp.getList().add(user);
                    } else {
                        for (int i = 0; i < ConsoleApp.getList().size(); i++) {
                            if (ConsoleApp.getList().get(i).equals(user.getName())) {
                                System.out.println("User already exists");
                                exists = true;
                            }
                        }
                        if (!exists) {
                            ConsoleApp.getList().add(user);
                        }
                    }
                    break;
                case 2:
                case 3:
                case 4:
                    for (int i = 0; i < ConsoleApp.getList().size(); i++) {
                        if (ConsoleApp.getList().get(i).getName().equals(item)) {
                            user = ConsoleApp.getList().get(i);
                        }
                    }
                    break;
            }

            switch (option) {
                case 1: // ADD User
                    if (users.isEmpty()) {
                        db.store(user);
                        db.commit();
                    } else {
                        System.out.println("ERROR");
                    }

                    break;
                case 2: // UPDATE Username
                    if (!users.isEmpty()) {
                        user.updateComponent(1);
                        userQuery = users.iterator().next();
                        userQuery.setName(user.getName());
                        userQuery.setPassword(userQuery.getPassword());
                        db.store(userQuery);
                        db.commit();
                    } else {
                        System.out.println("ERROR!");
                    }
                    break;
                case 3: // UPDATE Password
                    if (!users.isEmpty()) {
                        user.updateComponent(2);
                        userQuery = users.iterator().next();
                        userQuery.setPassword(user.getPassword());
                        db.store(userQuery);
                        db.commit();
                    } else {
                        System.out.println("ERROR!!");
                    }
                    break;
                case 4: // DELETE
                    if (!users.isEmpty()) {
                        userQuery = users.iterator().next();
                        db.delete(userQuery);
                        db.commit();
                        ConsoleApp.getList().remove(user);
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
            User userL = new User();
            ObjectSet<User> resultU = db.queryByExample(userL);
            while (resultU.hasNext()) {
                User inputU = resultU.next();
                ConsoleApp.setUser(inputU);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR DB4O LOAD");
        } finally {
            stopConnection();
        }
    }

}
