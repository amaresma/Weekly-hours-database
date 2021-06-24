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

    //private static String jdbcDatabase = "";
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

    public Work addWork(Work work) throws WeeklyHoursDatabaseException {
        if (work == null) {
            work = Work.addWork();
        } else {
            workList.add(work);
        }
        return work;
    }
    
    public Work selectWork(Work work) throws WeeklyHoursDatabaseException {
        if (work == null) {
            work = Work.selectMonthYear();
        }    
        return work;
    }
    
    public Work deleteWork (Work work) throws WeeklyHoursDatabaseException {
        if (work == null) {
            work = Work.selectDayMonthYear();
        }
        return work;
    }

}
