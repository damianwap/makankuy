/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author DAMIAN
 */
public class Konek {
    private static Connection conn = null;
    public static Connection getConnect(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:d:\\Users\\WINDOWS\\Desktop\\makankuy\\MakanKuy.db");
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Database tidak ditemukan");
        }
        return conn;
    }
}
