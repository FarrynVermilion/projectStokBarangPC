/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validasiInput;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 *
 * @author Pixy
 */
@WebServlet("/BarangController")
public class BarangController extends HttpServlet {
    private DAO_barang dao = new DAO_barang();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aksi = request.getParameter("aksi");
        boolean hasil = false;

        if ("delete".equalsIgnoreCase(aksi)) {
            //bwat dlet ambik punya id
            String id = request.getParameter("id");
            hasil = dao.delete(id);

        } else if ("insert".equalsIgnoreCase(aksi) || "update".equalsIgnoreCase(aksi)) {
            // for insert takee all id and updet
            String id = request.getParameter("id");
            String nama_barang = request.getParameter("nama_barang");

            Date tanggal_pembelian = null;
            try {
                tanggal_pembelian = Date.valueOf(request.getParameter("tanggal_pembelian"));
            } catch (IllegalArgumentException e) {
                tanggal_pembelian = null; // 
            }

            int jumlah = Integer.parseInt(request.getParameter("jumlah"));
            float harga_satuan = Float.parseFloat(request.getParameter("harga_satuan"));
            String vendor = request.getParameter("vendor");

            Date tanggal_garansi = null;
            String tglGaransiStr = request.getParameter("tanggal_garansi");
            if (tglGaransiStr != null && !tglGaransiStr.isEmpty()) {
                tanggal_garansi = Date.valueOf(tglGaransiStr);
            }

            String jenis_garansi = request.getParameter("jenis_garansi");
            String penanggung_jawab = request.getParameter("penanggung_jawab");

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

            if ("insert".equalsIgnoreCase(aksi)) {
                hasil = dao.insert(b);
            } else {
                hasil = dao.update(b);
            }
        }

        request.setAttribute("message", hasil ? "Operasi berhasil" : "Operasi gagal");
        ArrayList<barang> list = dao.getAll();
        request.setAttribute("dataBarang", list);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<barang> list = dao.getAll();
        request.setAttribute("dataBarang", list);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
