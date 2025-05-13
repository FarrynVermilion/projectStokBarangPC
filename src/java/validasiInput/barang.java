/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validasiInput;

import java.sql.Date;

// date dibikin pake dropdown aja bang html nunjukin nya mm/dd/yyyy 
/**
 *
 * @author edelweiss
 */
public class barang {
    private String id;
    private String nama_barang;
    private Date tanggal_pembelian;
    private int jumlah;
    private float harga_satuan;
    private String vendor;
    private Date tanggal_garansi;
    private String jenis_garansi;
    private String penanggung_jawab;
    private Date created_at;
    

    public String getId() {
        return id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public Date getTanggal_pembelian() {
        return tanggal_pembelian;
    }

    public int getJumlah() {
        return jumlah;
    }

    public float getHarga_satuan() {
        return harga_satuan;
    }

    public String getVendor() {
        return vendor;
    }

    public Date getTanggal_garansi() {
        return tanggal_garansi;
    }

    public String getJenis_garansi() {
        return jenis_garansi;
    }

    public String getPenanggung_jawab() {
        return penanggung_jawab;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public void setTanggal_pembelian(Date tanggal_pembelian) {
        this.tanggal_pembelian = tanggal_pembelian;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setHarga_satuan(float harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setTanggal_garansi(Date tanggal_garansi) {
        this.tanggal_garansi = tanggal_garansi;
    }

    public void setJenis_garansi(String jenis_garansi) {
        this.jenis_garansi = jenis_garansi;
    }

    public void setPenanggung_jawab(String penanggung_jawab) {
        this.penanggung_jawab = penanggung_jawab;
    }
    
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    
}
