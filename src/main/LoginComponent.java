/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

/**
 *
 * @author Albert
 */
public interface LoginComponent {
    public final static Scanner DATA = new Scanner(System.in);
    public void updatePassword()throws WeeklyHoursDatabaseException;
    public void updateUsername() throws WeeklyHoursDatabaseException;
    public void showComponent();
    public void deleteComponent()throws WeeklyHoursDatabaseException;
}
