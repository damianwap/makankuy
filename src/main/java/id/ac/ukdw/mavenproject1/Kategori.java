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
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DAMIAN
 */
public class Kategori implements Initializable {
    @FXML
    private Label tambahkatlbl,editkatlbl,hapuskatlbl,namalbl, keluarlbl, profillbl, tambahdatalbl, grafiklbl;
    
    @FXML
    private ImageView logo_makankuy;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void tambahkat(){
       try{
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TambahKategori1.fxml"));
           Parent signin = (Parent) loader.load();
           TambahKat hm=loader.getController();
           hm.setnama(this.namalbl.getText());
           Scene masuk = new Scene(signin);
           Stage app_stage  = (Stage) this.tambahkatlbl.getScene().getWindow();
           app_stage.close();
           app_stage.setScene(masuk);
           app_stage.show();
       } catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void editkat(){
       try{
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EditKategori.fxml"));
           Parent signin = (Parent) loader.load();
           EditKategori hm=loader.getController();
           hm.setnama(this.namalbl.getText());
           Scene masuk = new Scene(signin);
           Stage app_stage  = (Stage) this.editkatlbl.getScene().getWindow();
           app_stage.close();
           app_stage.setScene(masuk);
           app_stage.show();
       } catch(Exception e){
           e.printStackTrace();
       } 
    }
    
    public void hapuskat(){
        try{
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/HapusKategori.fxml"));
           Parent signin = (Parent) loader.load();
           HapusKategori hm=loader.getController();
           hm.setnama(this.namalbl.getText());
           Scene masuk = new Scene(signin);
           Stage app_stage  = (Stage) this.hapuskatlbl.getScene().getWindow();
           app_stage.close();
           app_stage.setScene(masuk);
           app_stage.show();
       } catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void grafik() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grafik.fxml"));
            Parent signin = (Parent) loader.load();
            Grafik hm = loader.getController();
            System.out.println("sampe sini");
            hm.setnama(this.namalbl.getText());
            hm.tampilgrafik();
            Scene masuk = new Scene(signin);
            Stage app_stage = (Stage) this.grafiklbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        } catch (Exception e) {
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
    
    public void home(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent signin = (Parent) loader.load();
            Home hm=loader.getController();
            hm.setnama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.logo_makankuy.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void keluar(){
        try{
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                 Parent signin = (Parent) loader.load();
                 Scene masuk = new Scene(signin);
                 Stage app_stage  = (Stage) this.keluarlbl.getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setNama(String nama){
        this.namalbl.setText(nama);
    }
    
    public void mouseinKeluar(){
        this.keluarlbl.setCursor(Cursor.HAND);
    }
}
