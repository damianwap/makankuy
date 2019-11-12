/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author DAMIAN
 */
public class TambahMakanMinum {
    @FXML
    private ComboBox pilihcb, pilihnama;
    
    @FXML
    private TextField inputmkn;
    
    @FXML
    private Button tambahbtn;
    
    @FXML 
    private Label namalbl, profillbl, kategorilbl, keluarlbl;
    
    @FXML
    private ImageView logo_makankuy;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
    public void tambahmakanminum(){
        pilihnama.getItems().clear();
        try {
            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("select nama_kat from kategori where jenis_kat='"+this.pilihcb.getValue().toString()+"'");
            while(rs.next()){
                pilihnama.getItems().add(rs.getString("nama_kat"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tambahbutton(ActionEvent ae){
        String namamakan = inputmkn.getText();
        String kat = pilihcb.getValue().toString();
        String jenis = pilihnama.getValue().toString();
        conn = Konek.getConnect();
        try {
            st = conn.createStatement();
            st.executeUpdate("insert into tambah_makan_minum (id_user, jenis_kat, nama_kat, nama_makan_minum) values((select user_id from user where nama = '"+this.namalbl.getText()+"'), '"+kat+"','"+jenis+"', '"+namamakan+"')");
            System.out.println("insert into tambah_makan_minum (id_user, jenis_kat, nama_kat, nama_makan_minum) values((select user_id from user where nama = '"+this.namalbl.getText()+"'), '"+kat+"','"+jenis+"', '"+namamakan+"')");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Berhasil yo");
            alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                Parent signin = (Parent) loader.load();
                Home hm = loader.getController();
                hm.setnama(this.namalbl.getText());
                Scene masuk = new Scene(signin);
                Stage app_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                app_stage.close();
                app_stage.setScene(masuk);
                app_stage.show();
                
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                st.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setnama(String nama){
        this.namalbl.setText(nama);
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
    
    
}

