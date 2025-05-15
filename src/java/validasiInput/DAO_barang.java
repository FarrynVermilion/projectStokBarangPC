package validasiInput;

import java.sql.*;
import java.util.ArrayList;
import koneksi.Database;
import java.text.*;

public class DAO_barang {
    Connection conn = Database.KoneksiDB();
    String INSERT = "INSERT INTO barang (id,nama_barang,tanggal_pembelian,jumlah,harga_satuan,vendor,tanggal_garansi,jenis_garansi,penanggung_jawab) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE = "UPDATE barang SET nama_barang=?,tanggal_pembelian=?,jumlah=?,harga_satuan=?,vendor=?,tanggal_garansi=?,jenis_garansi=?,penanggung_jawab=? WHERE id=?";
    String SELECT = "SELECT * FROM barang WHERE id LIKE ?";
    String DELETE = "DELETE FROM barang WHERE id = ?";
    String ALL = "SELECT * FROM barang";

    SimpleDateFormat sql = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat show = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat timestamps = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void insert(barang object) {
        try {
            PreparedStatement st = conn.prepareStatement(SELECT);
            st.setString(1, "%" + object.getId() + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("Data sudah ada");
            } else {
                st = conn.prepareStatement(INSERT);
                st.setString(1, object.getId());
                st.setString(2, object.getNama_barang());
                st.setDate(3, object.getTanggal_pembelian());
                st.setInt(4, object.getJumlah());
                st.setFloat(5, object.getHarga_satuan());
                st.setString(6, object.getVendor());
                st.setDate(7, object.getTanggal_garansi());
                st.setString(8, object.getJenis_garansi());
                st.setString(9, object.getPenanggung_jawab());
                st.executeUpdate();
                System.out.println("Insert sukses");
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(barang object) {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, object.getNama_barang());
            st.setDate(2, object.getTanggal_pembelian());
            st.setInt(3, object.getJumlah());
            st.setFloat(4, object.getHarga_satuan());
            st.setString(5, object.getVendor());
            st.setDate(6, object.getTanggal_garansi());
            st.setString(7, object.getJenis_garansi());
            st.setString(8, object.getPenanggung_jawab());
            st.setString(9, object.getId());
            st.executeUpdate();
            System.out.println("Data berhasil diubah");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            PreparedStatement st = conn.prepareStatement(DELETE);
            st.setString(1, id);
            st.executeUpdate();
            st.close();
            System.out.println("Hapus sukses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<barang> getCari(String key) {
        ArrayList<barang> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(SELECT);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                barang objBarang = new barang();
                objBarang.setId(rs.getString("id"));
                objBarang.setNama_barang(rs.getString("nama_barang"));
                objBarang.setTanggal_pembelian(rs.getDate("tanggal_pembelian"));
                objBarang.setJumlah(rs.getInt("jumlah"));
                objBarang.setHarga_satuan(rs.getFloat("harga_satuan"));
                objBarang.setVendor(rs.getString("vendor"));
                objBarang.setTanggal_garansi(rs.getDate("tanggal_garansi"));
                objBarang.setJenis_garansi(rs.getString("jenis_garansi"));
                objBarang.setPenanggung_jawab(rs.getString("penanggung_jawab"));
                objBarang.setCreated_at(rs.getTimestamp("created_at"));
                list.add(objBarang);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<barang> ALL() {
        ArrayList<barang> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(ALL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                barang objBarang = new barang();
                objBarang.setId(rs.getString("id"));
                objBarang.setNama_barang(rs.getString("nama_barang"));
                objBarang.setTanggal_pembelian(rs.getDate("tanggal_pembelian"));
                objBarang.setJumlah(rs.getInt("jumlah"));
                objBarang.setHarga_satuan(rs.getFloat("harga_satuan"));
                objBarang.setVendor(rs.getString("vendor"));
                objBarang.setTanggal_garansi(rs.getDate("tanggal_garansi"));
                objBarang.setJenis_garansi(rs.getString("jenis_garansi"));
                objBarang.setPenanggung_jawab(rs.getString("penanggung_jawab"));
                objBarang.setCreated_at(rs.getTimestamp("created_at"));
                list.add(objBarang);
            }
            st.close();
            System.out.println("All data retrieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
