/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import main.Component;
import main.WeeklyHoursDatabaseException;

/**
 *
 * @author Albert
 */
public class Day implements Component, Serializable{
    private int day;
    private String user;
    private Map<User, Day> selectDay = new HashMap();

    @Override
    public void updateComponent() throws WeeklyHoursDatabaseException {
    }

    @Override
    public void showComponent() {
    }
    
}
