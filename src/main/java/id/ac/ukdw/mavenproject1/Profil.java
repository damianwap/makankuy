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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Mery
 */
public class Profil implements Initializable {
    @FXML
    private Label namalbl,namalengkaplbl,emaillbl,tgllbl,jklbl,keluarlbl,kategorilbl;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    
    public void hapusbutton(ActionEvent ae){
         conn = Konek.getConnect();
        
        
        try{
            st = conn.createStatement();
            st.executeUpdate("delete from user where nama='"+this.namalbl.getText()+"'");
            JOptionPane.showMessageDialog(null, "berhasil hapus");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                 Parent signin = (Parent) loader.load();
                 Scene masuk = new Scene(signin);
                 Stage app_stage  = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
        }catch(Exception e){
            
        }finally{
            try{
                conn.close();
                rs.close();
                st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

public void setnama(String nama){
        this.namalbl.setText(nama);
    }

public void setnamalengkap(String nama){
    this.namalengkaplbl.setText(nama);
}

public void setemail(String nama){
    this.emaillbl.setText(nama);
}

public void settanggal(String nama){
    this.tgllbl.setText(nama);
}

public void setjeniskel(String nama){
    this.jklbl.setText(nama);
}

public void kategori(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Kategori.fxml"));
            Parent signin = (Parent) loader.load();
            TambahKatController hm=loader.getController();
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
