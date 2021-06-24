/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import login.Admin;
import main.WeeklyHoursDatabaseException;
import login.Login;
import login.User;
import main.ConsoleApp;
import sheet.Work;

/**
 *
 * @author Albert
 */
public class JDBCManager implements PersistanceProvider {
    // username: root password: whdb1234

    private static ConsoleApp app;
    private Admin admin;
    private User user = new User();
    private Work work;

    String userName;

    private Connection conn;

    private static String workSQL = "SELECT * FROM WORKSHEET WHERE USERS=? AND "
            + "DAYS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement workSt;
    private static String monthWorkSQL = "SELECT * FROM WORKSHEET WHERE USERS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement monthWorkSt;
    private static String insertWorkSQL = "INSERT INTO WORKSHEET(USERS,DAYS,MONTHS,YEARS,NORMALHOURS,NORMALMINUTES,EXTRAHOURS,EXTRAMINUTES)VALUES(?,?,?,?,?,?,?,?)";
    private PreparedStatement insertWorkSt;
    private static String updateWorkSQL = "UPDATE WORKSHEET SET DAYS=?,MONTHS=?,YEARS=?,NORMALHOURS=?,NORMALMINUTES=?,EXTRAHOURS=?,EXTRAMINUTES=? WHERE USERS=? AND DAYS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement updateWorkSt;
    private static String deleteWorkSQL = "DELETE FROM WORKSHEET WHERE USERS=? AND DAYS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement deleteWorkSt;
    private static String holidaySQL = "SELECT * FROM HOLIDAYS WHERE USERS=? AND DAYS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement holidaySt;
    private static String insertHolidaySQL = "INSERT INTO HOLIDAYS(USERS,DAYS,MONTHS,YEARS)VALUES(?,?,?,?)";
    private PreparedStatement insertHolidaySt;
    private static String updateHolidaySQL = "UPDATE HOLIDAYS SET DAYS=?,MONTHS=?,YEARS=? WHERE USER=? AND DAYS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement updateHolidaySt;
    private static String deleteHolidaySQL = "DELETE FROM HOLIDAYS WHERE USER=? AND DAYS=? AND MONTHS=? AND YEARS=?";
    private PreparedStatement deleteHolidaySt;

    public void startConnection() throws SQLException {
        String urlDatabase = "jdbc:derby://localhost:1527/WeeklyHoursDatabase";
        String userDB = "root";
        String password = "whdb1234";

        try {
            conn = DriverManager.getConnection(urlDatabase, userDB, password);
            workSt = conn.prepareStatement(workSQL);
            monthWorkSt = conn.prepareStatement(monthWorkSQL);
            insertWorkSt = conn.prepareStatement(insertWorkSQL);
            updateWorkSt = conn.prepareStatement(updateWorkSQL);
            deleteWorkSt = conn.prepareStatement(deleteWorkSQL);
            holidaySt = conn.prepareStatement(holidaySQL);
            insertHolidaySt = conn.prepareStatement(insertHolidaySQL);
            updateHolidaySt = conn.prepareStatement(updateHolidaySQL);
            deleteHolidaySt = conn.prepareStatement(deleteHolidaySQL);
        } catch (SQLException e) {
            conn = null;
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void stopConnection() throws SQLException {
        try {
            conn.close();
        } finally {
            conn = null;
        }
    }

    @Override
    public void save(String database, String item, int option) throws WeeklyHoursDatabaseException {
        int day = 0, month = 0, year = 0, normalHours = 0, normalMinutes = 0, extraHours = 0, extraMinutes = 0;
        userName = ConsoleApp.getUser().getName();
        switch (option) {
            case 1: // ADD WORK variables
                work = user.addWork(null);
                day = work.getDay();
                month = work.getMonth();
                year = work.getYear();
                normalHours = work.getNormalHours();
                normalMinutes = work.getNormalMinutes();
                extraHours = work.getExtraHours();
                extraMinutes = work.getExtraMinutes();
                break;
            case 2: // UPDATE WORK variables
                work = Work.selectDayMonthYear();
                day = work.getDay();
                month = work.getMonth();
                year = work.getYear();
                break;
            case 3: // DELETE WORK variables
                work = user.deleteWork(null);
                day = work.getDay();
                month = work.getMonth();
                year = work.getYear();
                break;
            case 4: // ADD HOLIDAY variables
                break;
            case 5: // UPDATE HOLIDAY variables
                break;
            case 6: // DELETE HOLIDAY variables
                break;
        }

        try {
            if (conn == null) {
                startConnection();
            }
            ResultSet resultSet = null;
            switch (option) { // SEARCH THE VALUES
                case 1:
                case 2:
                case 3:
                    workSt.setString(1, userName);
                    workSt.setInt(2, day);
                    workSt.setInt(3, month);
                    workSt.setInt(4, year);
                    resultSet = workSt.executeQuery();
                    break;
                case 4:
                case 5:
                case 6:
                    holidaySt.setString(1, app.getUser().getName());
                    // Missing the values assigned in user
                    resultSet = holidaySt.executeQuery();
                    break;
            }

            switch (option) {
                case 1: // ADD WORK
                    if (!resultSet.next()) {
                        insertWorkSt.setString(1, userName);
                        insertWorkSt.setInt(2, day);
                        insertWorkSt.setInt(3, month);
                        insertWorkSt.setInt(4, year);
                        insertWorkSt.setInt(5, normalHours);
                        insertWorkSt.setInt(6, normalMinutes);
                        insertWorkSt.setInt(7, extraHours);
                        insertWorkSt.setInt(8, extraMinutes);
                        insertWorkSt.executeUpdate();
                    }
                    break;
                case 2: // UPDATE WORK
                    if (resultSet.next()) {
                        Work workMod = new Work();
                        workMod = user.addWork(null);
                        updateWorkSt.setInt(1, workMod.getDay());
                        updateWorkSt.setInt(2, workMod.getMonth());
                        updateWorkSt.setInt(3, workMod.getYear());
                        updateWorkSt.setInt(4, workMod.getNormalHours());
                        updateWorkSt.setInt(5, workMod.getNormalMinutes());
                        updateWorkSt.setInt(6, workMod.getExtraHours());
                        updateWorkSt.setInt(7, workMod.getExtraMinutes());
                        updateWorkSt.setString(8, userName);
                        updateWorkSt.setInt(9, day);
                        updateWorkSt.setInt(10, month);
                        updateWorkSt.setInt(11, year);
                        updateWorkSt.executeUpdate();
                    }
                    break;
                case 3: // DELETE WORK

                    if (resultSet.next()) {
                        deleteWorkSt.setString(1, userName);
                        deleteWorkSt.setInt(2, day);
                        deleteWorkSt.setInt(3, month);
                        deleteWorkSt.setInt(4, year);
                        deleteWorkSt.executeUpdate();
                    }
                    break;
                case 4: // ADD HOLIDAY
                    break;
                case 5: // UPDATE HOLIDAY
                    break;
                case 6: // DELETE HOLIDAY
                    break;
            }
            stopConnection();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void load(String database, String item, int option) throws WeeklyHoursDatabaseException {
        try {
            userName = ConsoleApp.getUser().getName();
            if (conn == null) {
                startConnection();

                switch (option) {
                    case 1:
                        work = user.selectWork(null);
                        monthWorkSt.setString(1, userName);
                        monthWorkSt.setInt(2, work.getMonth());
                        monthWorkSt.setInt(3, work.getYear());
                        ResultSet resultSet = monthWorkSt.executeQuery();
                        while (resultSet.next()) {
                            System.out.println("USER: " + resultSet.getString("USERS"));
                            System.out.println("DAY: " + resultSet.getInt("DAYS"));
                            System.out.println("MONTH: " + resultSet.getInt("MONTHS"));
                            System.out.println("YEARS: " + resultSet.getInt("YEARS"));
                        }
                        break;
                    case 2:
                        break;
                }
            }
        } catch (SQLException ex) {
        }

    }

}
