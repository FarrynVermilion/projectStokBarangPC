/**
 *
 * @author Pixy
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
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

    private DAO_barang dao = new DAO_barang();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
            System.out.println(tanggal_garansi_str);
            // Parsing tipe data yang diperlukan
            Date tanggal_pembelian = dateFormat.parse(tanggal_pembelian_str);
            Date tanggal_garansi = dateFormat.parse(tanggal_garansi_str);
            int jumlah = Integer.parseInt(jumlah_str);
            float harga_satuan = Float.parseFloat(harga_satuan_str);
            
            
            // Buat objek barang
            barang b = new barang();
            b.setId(id);
            b.setNama_barang(nama_barang);
            b.setTanggal_pembelian(tanggal_pembelian);
            b.setJumlah(jumlah);
            b.setHarga_satuan(harga_satuan);
            b.setVendor(vendor);
            b.setTanggal_garansi(tanggal_garansi);
            b.setJenis_garansi(jenis_garansi);
            b.setPenanggung_jawab(penanggung_jawab);
            
            String sbmt = request.getParameter("submit");
            if(sbmt.equals("simpan")){
                // Insert ke database
                dao.insert(b);
            }
            if(sbmt.equals("update")){
                dao.update(b);
            }

            // Pop Up / Redirect taro sini
            request.setAttribute("message", "Data berhasil disimpan!");
            request.setAttribute("status", "success");
        } catch (Exception e) {
            System.out.println("error"+e.getMessage());
            e.printStackTrace();
            request.setAttribute("message", "Terjadi kesalahan saat menyimpan data.");
            request.setAttribute("status", "error");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    //get
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
