package id.ac.ukdw.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import id.ac.ukdw.Koneksi.Konek;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Angel
 */
public class Login implements Initializable {
    @FXML
    private Label register_label;
    
    @FXML
    private Button loginbtn;
    
    @FXML
    private TextField emailtxt;
    
    @FXML
    private PasswordField passwordtxt;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

Connection conn;

Statement st;

ResultSet rs;

public void login(ActionEvent aksi) throws IOException, ClassNotFoundException, SQLException{
     conn = Konek.getConnect();
     String email = emailtxt.getText();
     String password = passwordtxt.getText();
     
     try{
         st = conn.createStatement();
         rs = st.executeQuery("SELECT * from user WHERE email = '"+email+"'");

//         System.out.println(password);
         if(rs.next()){
             if(password.equals(rs.getString("Password"))){
                 JOptionPane.showMessageDialog(null, "Selamat Datang," + rs.getString("nama"));
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                 Parent signin = (Parent) loader.load();
                 Home hm=loader.getController();
                 hm.setnama(rs.getString("nama"));
                 Scene masuk = new Scene(signin);
                 Stage app_stage  = (Stage) ((Node) aksi.getSource()).getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
                 
             }else{
                 JOptionPane.showMessageDialog(null, "Email atau password salah!");
             }
         }else{
             JOptionPane.showMessageDialog(null, "Email atau password salah1!");
         }
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

    public void mouseinlogin(){
        loginbtn.setCursor(Cursor.HAND);
    }
    public void register(){
        Stage stage= (Stage) register_label.getScene().getWindow();
        stage.close();
        
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Parent root = (Parent) loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void mouseinregister(){
        register_label.setCursor(Cursor.HAND);
    }
}
