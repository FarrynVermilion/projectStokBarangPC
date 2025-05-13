/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validasiInput;
import java.sql.*;
import java.util.*;
import java.text.*;
import koneksi.Database;
/**
 *
 * @author edelweiss
 */
public class DAO_barang {
    Connection conn = Database.KoneksiDB();
    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat TimestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    String INSERT = "INSERT INTO barang (id,nama_barang,tanggal_pembelian,jumlah,harga_satuan,vendor,tanggal_garansi,jenis_garansi,penanggung_jawab) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE = "UPDATE barang SET nama_barang=?,tanggal_pembelian=?,jumlah=?,harga_satuan=?,vendor=?,tanggal_garansi=?,jenis_garansi=?,penanggung_jawab=? WHERE id=?";
    String SELECT = "SELECT * FROM barang WHERE id LIKE ?";
    String DELETE = "DELETE FROM barang WHERE id = ? ";
    String ALL = "SELECT * FROM barang";
    public void insert(barang object){    
        try{
            PreparedStatement st = conn.prepareStatement(SELECT);
            st.setString(1,"%"+object.getId()+"%");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                System.out.println("Data already exist");
            }
            else{
                st = conn.prepareStatement(INSERT);
                st.setString(1, object.getId());
                st.setString(2, object.getNama_barang());
                st.setString(3, DateFormat.format(object.getTanggal_pembelian()));
                st.setString(4, String.valueOf(object.getJumlah()));
                st.setString(5, String.valueOf(object.getHarga_satuan()));
                st.setString(6, object.getVendor());
                st.setString(7, DateFormat.format(object.getTanggal_garansi()));
                st.setString(8, object.getJenis_garansi());
                st.setString(9, object.getPenanggung_jawab());
                st.executeUpdate();
                System.out.println("Insert success");
            }
            st.close();
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }
    public void update(barang object){
        try{
            PreparedStatement st=conn.prepareStatement(UPDATE);
            st.setString(1, object.getNama_barang());
            st.setString(2, DateFormat.format(object.getTanggal_pembelian()));
            st.setString(3, String.valueOf(object.getJumlah()));
            st.setString(4, String.valueOf(object.getHarga_satuan()));
            st.setString(5, object.getVendor());
            st.setString(6, DateFormat.format(object.getTanggal_garansi()));
            st.setString(7, object.getJenis_garansi());
            st.setString(8, object.getPenanggung_jawab());
            st.setString(9, object.getId());
            st.executeUpdate();
            System.out.println("Data berhasil diubah");
            st.close();
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }
    public void delete(String id ){
        try{
            PreparedStatement st=conn.prepareStatement(DELETE);
            st.setString(1, id );
            st.executeUpdate();
            st.close();
            System.out.println("Hapus sukses");
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }
    public ArrayList<barang> getCari(String key){
        ArrayList<barang> List = new ArrayList<barang>();
        try{
            PreparedStatement st=conn.prepareStatement(SELECT);
            st.setString(1, "%"+key+"%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                barang objBarang = new barang();
                objBarang.setId(rs.getString("id"));
                objBarang.setNama_barang(rs.getString("nama_barang"));
                objBarang.setTanggal_pembelian(rs.getDate("tanggal_pembelian"));
                objBarang.setJumlah(rs.getInt("jumlah"));
                objBarang.setHarga_satuan(rs.getInt("harga_satuan"));
                objBarang.setVendor(rs.getString("vendor"));
                objBarang.setTanggal_garansi(rs.getDate("tanggal_garansi"));
                objBarang.setJenis_garansi(rs.getString("jenis_garansi"));
                objBarang.setPenanggung_jawab(rs.getString("penanggung_jawab"));
                objBarang.setCreated_at(rs.getDate("created_at"));
                List.add(objBarang);
            }
            st.close();
        }
        catch (Exception E){
            E.printStackTrace();
        }
        return List;
    }
    public ArrayList<barang> ALL(){
        ArrayList<barang> List = new ArrayList<barang>();
        try{
            PreparedStatement st=conn.prepareStatement(ALL);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                barang objBarang = new barang();
                objBarang.setId(rs.getString("id"));
                objBarang.setNama_barang(rs.getString("nama_barang"));
                objBarang.setTanggal_pembelian(rs.getDate("tanggal_pembelian"));
                objBarang.setJumlah(rs.getInt("jumlah"));
                objBarang.setHarga_satuan(rs.getInt("harga_satuan"));
                objBarang.setVendor(rs.getString("vendor"));
                objBarang.setTanggal_garansi(rs.getDate("tanggal_garansi"));
                objBarang.setJenis_garansi(rs.getString("jenis_garansi"));
                objBarang.setPenanggung_jawab(rs.getString("penanggung_jawab"));
                objBarang.setCreated_at(rs.getDate("created_at"));
                List.add(objBarang);
                System.out.println("All data retrieved");
            }
            st.close();
        }
        catch (Exception E){
            E.printStackTrace();
        }
        return List;
    }
}
