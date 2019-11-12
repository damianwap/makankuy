/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
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
public class SignUp implements Initializable{

    @FXML
    private RadioButton pilih_lakilaki,pilih_perempuan;
    
    @FXML
    private Label loginlbl;
    
    @FXML
    private TextField namatxt, emailtxt;
    
    @FXML
    private PasswordField passwordtxt;
    
    @FXML
    private DatePicker tanggallahir;
    
    @FXML
    private Button daftarbtn;
    
    Connection conn;

    Statement st;

    ResultSet rs;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void selectlaki(){
        pilih_perempuan.setSelected(false);
        pilih_lakilaki.setSelected(true);
        
    }
    
    public void selectperempuan(){
        pilih_lakilaki.setSelected(false);
        
    }
    
    public void regis(ActionEvent ae ){
        String nama = namatxt.getText();
        String email = emailtxt.getText();
        String password = passwordtxt.getText();
        String date = tanggallahir.getValue().toString();
        String gender;
        if(pilih_perempuan.isSelected()){
            gender = "Perempuan";
        }else{
            gender = "Laki-laki";
        }
        
        try{
            conn = Konek.getConnect();
            st = conn.createStatement();
            st.execute("INSERT into user(nama,password,tanggal_lahir,jenis_kelamin,email) values('"+nama+"','"+password+"','"+date+"','"+gender+"','"+email+"')");
            JOptionPane.showMessageDialog(null, "Berhasil input");
            Parent signin = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
                 Scene masuk = new Scene(signin);
                 Stage app_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "gagal input");
        }
    }
    
    public void login(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent signin = (Parent) loader.load();
            SignIn hm=loader.getController();
            Scene masuk = new Scene(signin);
            Stage app_stage  = (Stage) this.loginlbl.getScene().getWindow();
            app_stage.close();
            app_stage.setScene(masuk);
            app_stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
