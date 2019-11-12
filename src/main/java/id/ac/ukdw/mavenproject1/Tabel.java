/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.mavenproject1;

/**
 *
 * @author WINDOWS
 */
public class Tabel {
       private String id_tabel;
       private String no;
       private String nama_makanan;
       private String kategori;
       private String tanggal;
       private String kalori;
       private String porsi;
       
    public Tabel (String id_tabel,String no, String nama_makanan, String kategori, String tanggal, String kalori, String porsi){
        this.id_tabel = id_tabel;
        this.no=no;
        this.nama_makanan = nama_makanan;
        this.kategori = kategori;
        this.tanggal = tanggal;
        this.kalori = kalori;
        this.porsi = porsi;
    }

    Tabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    
    public String getId_tabel() {
        return id_tabel;
    }

    public void setId_tabel(String id_tabel) {
        this.id_tabel = id_tabel;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKalori() {
        return kalori;
    }

    public void setKalori(String kalori) {
        this.kalori = kalori;
    }

    public String getPorsi() {
        return porsi;
    }

    public void setPorsi(String porsi) {
        this.porsi = porsi;
    }
       
       
}
