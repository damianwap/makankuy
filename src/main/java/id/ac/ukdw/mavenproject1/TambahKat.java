/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 *
 * @author DAMIAN
 */
public class TambahKat implements Initializable{

    @FXML
    private Label namalbl;
    
    
    @FXML
    private TextField namakat;
    
    @FXML
    private ComboBox pilihcb;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    
    
    public void tambahkat(ActionEvent ae){
        String kat=namakat.getText();
        String jenis=pilihcb.getValue().toString();
        conn=Konek.getConnect();
//        System.out.println("disini");
        try{
            st=conn.createStatement();
            st.executeUpdate("insert into kategori(id_user,nama_kat,jenis_kat) values((select user_id from user where nama='"+this.namalbl.getText()+"'),'"+kat+"','"+jenis+"')");
            JOptionPane.showMessageDialog(null, "BERHASIL INPUT");
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                 Parent signin = (Parent) loader.load();
                 Home hm=loader.getController();
                 hm.setnama(this.namalbl.getText());
                 Scene masuk = new Scene(signin);
                 Stage app_stage  = (Stage) ((Node) ae.getSource()).getScene().getWindow();
                 app_stage.close();
                 app_stage.setScene(masuk);
                 app_stage.show();
                 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
                st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void setnama(String nama){
        this.namalbl.setText(nama);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
