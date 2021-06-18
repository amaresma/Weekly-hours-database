/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.util.ArrayList;
import java.util.List;
import main.WeeklyHoursDatabaseException;
import persistance.JDBCManager;
import sheet.Work;

/**
 *
 * @author Albert
 */
public class User extends Login {

    private static JDBCManager jdbcManager = new JDBCManager();
    private static String jdbcDatabase = "";
    private List<Work> workList = new ArrayList();

    public User(String name, String password) {
        super(name, password);
    }

    public User() {
    }

    public List<Work> getWork() {
        return workList;
    }

    public void setWork(List<Work> workList) {
        this.workList = workList;
    }

    public void addWork(Work work) throws WeeklyHoursDatabaseException {
        if (work == null) {
            work = Work.addWork();
        } else {
            workList.add(work);
        }
        // Select component conditions needs to be done
        workList.add(work);
        for (int i = 0; i < workList.size(); i++) {
            System.out.println("DAY: " + workList.get(i).getDay()
                    + " MONTH: " + workList.get(i).getMonth()
                    + " YEAR: " + workList.get(i).getYear()
                    + " HOURS: " + workList.get(i).getNormalHours()
                    + " MINUTES: " + workList.get(i).getNormalMinutes());
        }

    }

}
