/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import id.ac.ukdw.Koneksi.Konek;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author DAMIAN
 */
public class Grafik {
    
    @FXML
    private Label namalbl, profillbl, tambahdatalbl, keluarlbl, kategorilbl, grafilklbl, tentanglbl;
    
    @FXML
    private Button grafikbtn;
    
    @FXML
    private LineChart<String, Double> lineChart;
    
       
    Connection conn;
    Statement st;
    ResultSet rs;
    
    
    public void tambahdata(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TambahMakanMinum.fxml"));
            Parent signin = (Parent) loader.load();
            TambahMakanMinum hm=loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.tambahdatalbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void profil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profil.fxml"));
            Parent signin = (Parent) loader.load();
            Profil hm = loader.getController();

            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("Select * from user where nama='" + this.namalbl.getText() + "'");
            System.out.println("sampe sini");

            hm.setnama(this.namalbl.getText());
            hm.setemail(rs.getString("email"));
            hm.setjeniskel(rs.getString("Jenis_kelamin"));
            hm.setnamalengkap(this.namalbl.getText());
            hm.settanggal(rs.getString("Tanggal_lahir"));
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.profillbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    

    
    public void tentang(){
        
    }
    
    public void grafik(){
        
    }
    
     public void tampilgrafik(){
        //"select * from data_makan_minum where id_user=(select user_id from user where nama='"+this.namalbl.getText()+"')"
        String query = "SELECT tanggal,kalori FROM data_makan_minum where id_user = (select user_id from user where nama = '"+this.namalbl.getText()+"')order by tanggal asc";
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Kalori");
        try{
            conn = Konek.getConnect();
            ResultSet rs = conn.createStatement().executeQuery(query);
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            lineChart.getData().add(series);
        }catch(Exception e){
            
        }
    }
    
    public void kategori() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Kategori.fxml"));
            Parent signin = (Parent) loader.load();
            Kategori hm = loader.getController();
            hm.setNama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.kategorilbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void keluar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent signin = (Parent) loader.load();
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.keluarlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setnama(String nama) {
        this.namalbl.setText(nama);
    }
}
