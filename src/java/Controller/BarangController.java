/**
 *
 * @author Pixy
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import validasiInput.DAO_barang;
import validasiInput.barang;

@WebServlet(name = "BarangController", urlPatterns = {"/BarangController"})
public class BarangController extends HttpServlet {

    private final DAO_barang dao = new DAO_barang();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Ambil parameter dari form
            String id = request.getParameter("id");
            String nama_barang = request.getParameter("nama_barang");
            String tanggal_pembelian_str = request.getParameter("tanggal_pembelian");
            String jumlah_str = request.getParameter("jumlah");
            String harga_satuan_str = request.getParameter("harga_satuan");
            String vendor = request.getParameter("vendor");
            String tanggal_garansi_str = request.getParameter("tanggal_garansi");
            String jenis_garansi = request.getParameter("jenis_garansi");
            String penanggung_jawab = request.getParameter("penanggung_jawab");

            // Parsing tipe data yang diperlukan
            Date tanggal_pembelian = tanggal_pembelian_str != null && !tanggal_pembelian_str.isEmpty()
                    ? dateFormat.parse(tanggal_pembelian_str) : null;
            Date tanggal_garansi = tanggal_garansi_str != null && !tanggal_garansi_str.isEmpty()
                    ? dateFormat.parse(tanggal_garansi_str) : null;
            int jumlah = jumlah_str != null && !jumlah_str.isEmpty() ? Integer.parseInt(jumlah_str) : 0;
            int harga_satuan = harga_satuan_str != null && !harga_satuan_str.isEmpty() ? Integer.parseInt(harga_satuan_str) : 0;

            // Buat objek barang
            barang b = new barang();
            b.setId(id);
            b.setNama_barang(nama_barang);
            if (tanggal_pembelian != null) {
                b.setTanggal_pembelian(new java.sql.Date(tanggal_pembelian.getTime()));
            } else {
                b.setTanggal_pembelian(null);
            }

            if (tanggal_garansi != null) {
                b.setTanggal_garansi(new java.sql.Date(tanggal_garansi.getTime()));
            } else {
                b.setTanggal_garansi(null);
            }

            b.setJumlah(jumlah);
            b.setHarga_satuan(harga_satuan);
            b.setVendor(vendor);

            b.setJenis_garansi(jenis_garansi);
            b.setPenanggung_jawab(penanggung_jawab);

            // Insert ke database
            dao.insert(b);

            // Redirect ke halaman sukses atau tampilkan pesan
            response.sendRedirect("index.jsp?message=Insert+Success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?message=Error+occurred");
        }
    }

    // Untuk GET bisa redirect ke form input
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
