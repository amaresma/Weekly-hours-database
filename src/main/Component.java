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
public interface Component {
    public final static Scanner DATA = new Scanner(System.in);
    public void updateComponent(int option);
    public void showComponent(int option);
}
