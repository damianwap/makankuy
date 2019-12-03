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
       private String pilih_kat;
       private String tanggal;
       private String kalori;
       private String porsi;
       private String waktu;
       
    public Tabel (String id_tabel,String no, String nama_makanan, String kategori,String pilih_kat, String tanggal, String kalori, String porsi, String waktu){
        this.id_tabel = id_tabel;
        this.no=no;
        this.nama_makanan = nama_makanan;
        this.kategori = kategori;
        this.pilih_kat = pilih_kat;
        this.tanggal = tanggal;
        this.kalori = kalori;
        this.porsi = porsi;
       this.waktu = waktu;
        
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
    

    /**
     * @return the pilih_kat
     */
    public String getPilih_kat() {
        return pilih_kat;
    }

    /**
     * @param pilih_kat the pilih_kat to set
     */
    public void setPilih_kat(String pilih_kat) {
        this.pilih_kat = pilih_kat;
    }

    /**
     * @return the waktu
     */
    public String getWaktu() {
        return waktu;
    }

    /**
     * @param waktu the waktu to set
     */
    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }



  
       
       
}
