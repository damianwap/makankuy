/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

import id.ac.ukdw.Koneksi.Konek;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author WINDOWS
 */
public class CRUD {
        Connection conn;
        
        
        public Tabel pilih(String nama_makan_minum){
        conn = Konek.getConnect();
        String selectStmt = "select * from data_makan_minum";
            try {
                ResultSet rs = conn.createStatement().executeQuery(selectStmt);
                Tabel makan = getMakananFromResultSet(rs);
                return makan;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }
//    public void hapusData(String nama_makan_minum)throws SQLException, ClassNotFoundException{
//        conn = Konek.getConnect();
//        String updateStmt = "delete from data_makan_minum where nama_makan_minum = '"+nama_makan_minum+"'";
//        try {
//            conn.createStatement().executeUpdate(updateStmt);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
        public static void hapus(String id_makan_minum)throws SQLException, ClassNotFoundException{
          //  conn = Konek.getConnect();
            String sql = "delete from data_makan_minum where id_makan_minum='"+id_makan_minum+"'";
            try {
                Konek.getConnect().createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                throw e;
            }
        }
        
        public static void update(String id_makan_minum, Tabel makan) throws SQLException, ClassNotFoundException{
            String sql = "update data_makan_minum set nama_makan_minum = '" + makan.getNama_makanan() + "'," + "kategori = '" + makan.getKategori().toString() + "'," + "tanggal = '" + makan.getTanggal().toString() + "'," + "porsi = '"+makan.getPorsi()+"'where id_makan_minum='" + makan.getId_tabel() + "'";
            System.out.println(sql);
            try{
                Konek.getConnect().createStatement().executeUpdate(sql);
            }catch(Exception e){
                throw e;
            }
        }
        
        public static void tambahMakan(Tabel makan)throws SQLException, ClassNotFoundException{
            String sql = "insert into data_makan_minum(nama_makan_minum, kategori, tanggal, porsi) values ('"+makan.getNama_makanan()+"', " + "'" +makan.getKategori().toString()+ "', "+"'"+makan.getTanggal().toString()+"', "+ "'"+makan.getPorsi()+"')";
            System.out.println(sql);
            try {
               Konek.getConnect().createStatement().executeUpdate(sql);
            } catch (Exception e) {
                throw e;
            }
        }

    private Tabel getMakananFromResultSet(ResultSet rs) throws SQLException {
       Tabel makan = null;
       if(rs.next()){
           makan = new Tabel();
           makan.setNama_makanan("nama_makan_minum");
           makan.setKategori("kategori");
           makan.setKalori("kalori");
           makan.setTanggal("tanggal");
           makan.setPorsi("porsi");
       }
       return makan;
       
    }
    
    
}
