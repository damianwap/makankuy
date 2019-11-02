/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;


import id.ac.ukdw.Koneksi.Konek;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 *
 * @author DAMIAN
 */
public class home implements Initializable {
    @FXML
    private Label totalkalori, namalbl,kategorilbl,profillbl,keluarlbl;
    
    @FXML
    private TableColumn idmakantbl,namamakantbl,tanggaltbl,kaloritbl;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    Connection conn;

    Statement st;

    ResultSet rs;
    
    /**
     *
     * @param aksi
     */
    private String nama;
    public void getNama(String nama){
        this.nama = nama;
        namalbl.setText("SELECT nama from user WHERE email = '"+nama+"'");
    }
    public void masuk(ActionEvent aksi){
        conn = Konek.getConnect();
        String email = namalbl.getText();
        try{
            st = conn.createStatement();
            rs = st.executeQuery("SELECT nama from user WHERE email = '"+email+"'");
            System.out.println("SELECT nama from user WHERE email = '"+email+"'");
        }catch(Exception e){
          
         e.printStackTrace();
     }finally{
         try{
             rs.close();
             st.close();
             conn.close();
         }catch(Exception e){
             e.printStackTrace();
         }
     }
    }
    
    public void setnama(String nama){
        this.namalbl.setText(nama);
    }
    
    public void kategori(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Kategori.fxml"));
            Parent signin = (Parent) loader.load();
            TambahkatController hm=loader.getController();
            hm.setNama(this.namalbl.getText());
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.kategorilbl.getScene().getWindow();
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
    
    public void profil(){
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/profil.fxml"));
            Parent signin = (Parent) loader.load();
            Profil hm=loader.getController();
            
            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("Select * from user where nama='"+this.namalbl.getText()+"'");
             System.out.println("sampe sini");
            
            hm.setnama(this.namalbl.getText());
            hm.setemail(rs.getString("email"));
            hm.setjeniskel(rs.getString("Jenis_kelamin"));
            hm.setnamalengkap(this.namalbl.getText());
            hm.settanggal(rs.getString("Tanggal_lahir"));
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.kategorilbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
         try{
             rs.close();
             st.close();
             conn.close();
         }catch(Exception e){
             e.printStackTrace();
         }
     }
    }
  }
    


