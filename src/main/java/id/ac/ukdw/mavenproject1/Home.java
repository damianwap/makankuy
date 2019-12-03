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
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author DAMIAN
 */
public class Home implements Initializable {

    @FXML
    private Label totalkalori, namalbl, kategorilbl, profillbl, keluarlbl, grafiklbl, tambahdatalbl;

    @FXML
    private TableColumn <Tabel, String> nomortbl, namamakantbl, kategoritbl, jenis_kat, tanggaltbl, kaloritbl, porsitbl, waktutbl;
    
    @FXML
    private TableView <Tabel> datatbl;
    
    @FXML
    private Button tambahmakanbtn, caribtn;
    
    @FXML
    private TextField fieldcari;
    
    
    
    private Alert EditDataMakanMinum;
    
    String nama_makan_minum;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    ResultSet rs2;
    public void initialize(URL location, ResourceBundle resources) {
        nomortbl.setCellValueFactory(new PropertyValueFactory("no"));
        namamakantbl.setCellValueFactory(new PropertyValueFactory("nama_makanan"));
        kategoritbl.setCellValueFactory(new PropertyValueFactory("kategori"));
        jenis_kat.setCellValueFactory(new PropertyValueFactory("pilih_kat"));
        tanggaltbl.setCellValueFactory(new PropertyValueFactory("tanggal"));
        kaloritbl.setCellValueFactory(new PropertyValueFactory("kalori"));
        porsitbl.setCellValueFactory(new PropertyValueFactory("porsi"));
        waktutbl.setCellValueFactory(new PropertyValueFactory("waktu"));
        
        
        
         final ContextMenu cxMenu = new ContextMenu();
        MenuItem cxMenuItemEdit = new MenuItem("Ubah Data");
        cxMenu.getItems().add(cxMenuItemEdit);
        MenuItem cxMenuItemDelete = new MenuItem("Hapus Data");
        cxMenu.getItems().add(cxMenuItemDelete);
        datatbl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cxMenu.show(datatbl, t.getScreenX(), t.getScreenY());
                }
                if (t.getButton() == MouseButton.PRIMARY) {
                    if (cxMenu.isShowing()) {
                        cxMenu.hide();
                    }
                }
            }
        });
        
        datatbl.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evt) {
                if (evt.getCode().equals(KeyCode.ESCAPE)) {
                    cxMenu.hide();
                }
            }
        });

        cxMenuItemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                conn = Konek.getConnect();
                try {
                    final Tabel makan = datatbl.getSelectionModel().getSelectedItem();
                    int index = datatbl.getSelectionModel().getSelectedIndex();
                    CRUD.hapus(makan.getId_tabel());
                    datatbl.getItems().remove(index);
                    isitabel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        cxMenuItemEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Tabel tbl =datatbl.getSelectionModel().getSelectedItem();
                openFormMakan(false,tbl);
            }
        });
    }
    

    /**
     *
     * @param aksi
     */
    private String nama;
    private String kalori;

    public void getNama(String nama) {
        this.nama = nama;
        namalbl.setText("SELECT nama from user WHERE email = '" + nama + "'");
    }
    
    public void getKal(String kalori){
         conn = Konek.getConnect();
         int tamp = 0;
         
         try{
             st = conn.createStatement();
             rs = st.executeQuery("SELECT * FROM data_makan_minum where id_user = (select user_id from user where nama = '"+this.namalbl.getText()+"')");
             while(rs.next()){
                 tamp = tamp + Integer.parseInt(rs.getString("kalori"))*Integer.parseInt(rs.getString("porsi"));
             }
             totalkalori.setText(String.valueOf(tamp)+" kal");
         }catch(Exception e){
             
         }
    }
    
    

    
    public void isitabel(){
        conn=Konek.getConnect();
        try{
            st = conn.createStatement();
            rs = st.executeQuery("select * from data_makan_minum where id_user=(select user_id from user where nama='"+this.namalbl.getText()+"')");
            int i=1;
            ObservableList<Tabel> isi=FXCollections.observableArrayList(); //nampung data dari tabel
            
            while(rs.next()){
                isi.add(new Tabel(
                        rs.getString("id_makan_minum"),
                        String.valueOf(i++),
                        rs.getString("nama_makan_minum"),
                        rs.getString("kategori"),
                        rs.getString("jenis_kat"),
                        rs.getString("tanggal"),
                        rs.getString("kalori"),
                        rs.getString("porsi"),
                        rs.getString("waktu")
                ));
            }
            this.datatbl.setItems(isi);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            }catch(Exception e){
                
            }
        }
        
    }
    public void masuk(ActionEvent aksi) {
        conn = Konek.getConnect();
        String email = namalbl.getText();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT nama from user WHERE email = '" + email + "'");
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

    public void setnama(String nama) {
        this.namalbl.setText(nama);
        isitabel();
        getKal("kalori");
    }
    
    public void setKal(String kalori){
        this.totalkalori.setText(kalori);
        
    }
    
   
    
      private void openFormMakan(boolean update, final Tabel makan){
        try{  
        int index = 0;
        if (!update) {
            index = datatbl.getSelectionModel().getSelectedIndex();
        }
        if (EditDataMakanMinum == null) {
            EditDataMakanMinum = new Alert(INFORMATION);
            EditDataMakanMinum.setTitle("Update Makananmu");
            EditDataMakanMinum.setHeaderText("Form Edit Makanan");
            EditDataMakanMinum.initModality(Modality.WINDOW_MODAL);
            ButtonType simpanbtn = new ButtonType("Simpan");
            EditDataMakanMinum.getButtonTypes().setAll(simpanbtn);

            FormMakanan makanan = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditDataMakanMinum.fxml"));
            try {
                Node konten = loader.load();
                DialogPane pane = EditDataMakanMinum.getDialogPane();
                pane.setContent(konten);
                makanan = loader.getController();
                makanan.setTabel(makan);
                EditDataMakanMinum.showAndWait();
            } catch (IOException e) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();

            }
            Optional<ButtonType> rs = EditDataMakanMinum.showAndWait();
            if (rs.get() == simpanbtn) {
                if (makanan != null) {
                    if (update) {
                        // tambah data ke tabel
                        Tabel makanUpdate = makanan.getTabel();
                        try {
                            CRUD.tambahMakan(makanUpdate);
                            datatbl.getItems().add(makanUpdate);
                        } catch (SQLException | ClassNotFoundException e) {
                            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } else {
                        String makanOld = makan.getNama_makanan();
                        Tabel makanUpdate = makanan.getTabel();
                        try {
                            ObservableList<Tabel> listTabel = datatbl.getItems();
                            CRUD.update(makanOld, makanUpdate);
                            listTabel.set(index, makanUpdate);

                        } catch (SQLException | ClassNotFoundException e) {
                            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
                EditDataMakanMinum = null;
            }
        }
        }catch(Exception e){
            e.printStackTrace();
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
    
    public void tambah_makan(){
        try{
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Input_Makanan.fxml"));
           Parent signin = (Parent) loader.load();
           InputMakan hm=loader.getController();
           hm.setnama(this.namalbl.getText());
           Scene masuk = new Scene(signin);
           Stage app_stage  = (Stage) this.tambahmakanbtn.getScene().getWindow();
           app_stage.close();
           app_stage.setScene(masuk);
           app_stage.show();
       } catch(Exception e){
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

    public void grafik() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Grafik.fxml"));
            Parent signin = (Parent) loader.load();
            Grafik hm = loader.getController();
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
    
    public void tentang(){
        
    }

    public void profil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Profil.fxml"));
            Parent signin = (Parent) loader.load();
            Profil hm = loader.getController();

            conn = Konek.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("Select * from user where nama='" + this.namalbl.getText() + "'");
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
    
    public void cari(){
        try{
            conn = Konek.getConnect();
            st = conn.createStatement();   
            rs = st.executeQuery("select * from data_makan_minum where id_user=(select user_id from user where nama='"+this.namalbl.getText()+"') and nama_makan_minum like '%"+this.fieldcari.getText()+"%'");
            int i=1;

            ObservableList<Tabel> isi=FXCollections.observableArrayList(); //nampung data dari tabel
            
            while(rs.next()){
                isi.add(new Tabel(
                        rs.getString("id_makan_minum"),
                        String.valueOf(i++),
                        rs.getString("nama_makan_minum"),
                        rs.getString("kategori"),
                        rs.getString("jenis_kat"),
                        rs.getString("tanggal"),
                        rs.getString("kalori"),
                        rs.getString("porsi"),
                        rs.getString("waktu")
                ));
            }
            this.datatbl.setItems(isi);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
