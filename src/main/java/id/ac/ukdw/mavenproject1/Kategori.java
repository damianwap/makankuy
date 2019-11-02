/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author DAMIAN
 */
public class Kategori implements Initializable{
    @FXML
    private ComboBox<String> kategoricb;
    ObservableList<String> list = FXCollections.observableArrayList("aku","cantik");
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet.");
        kategoricb.setItems(list);
        
    }
    
}
