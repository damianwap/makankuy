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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DAMIAN
 */
public class TambahKatController implements Initializable {
    @FXML
    private Label tambahkatlbl,editkatlbl,hapuskatlbl,namalbl, keluarlbl;
    
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void tambahkat(){
       try{
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/tambahkategori1.fxml"));
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
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/editkategori.fxml"));
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
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/hapuskategori.fxml"));
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
