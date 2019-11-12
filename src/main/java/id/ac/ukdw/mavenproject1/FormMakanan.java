/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author WINDOWS
 */
public class FormMakanan implements Initializable {
    private Tabel makan;
     
    @FXML
    private TextField namabaru, porsibaru;
    
    @FXML
    private ComboBox katbaru;
    
    @FXML
    private DatePicker tanggalbaru;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    public void setTabel(Tabel makanOld) throws ParseException{
        this.makan = makanOld;
        namabaru.setText(makan.getNama_makanan());
        katbaru.setValue(makan.getKategori().toString());
        porsibaru.setText(makan.getPorsi());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date tanggalbaru = format.parse(makanOld.getTanggal());
        this.tanggalbaru.setValue(tanggalbaru.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    
    public Tabel getTabel(){
        this.makan.setNama_makanan(namabaru.getText());
        this.makan.setKategori(katbaru.getValue().toString());
        this.makan.setPorsi(porsibaru.getText());
        this.makan.setTanggal(tanggalbaru.getValue().toString());
        return this.makan;
    }
   
    
}