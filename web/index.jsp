<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="validasiInput.DAO_barang" %>
<%@ page import="validasiInput.barang" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Input Barang</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        body {
            background-color: #f5f7fa;
            padding: 20px;
            margin: 0;
        }
        
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        
        h2 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 10px;
            border-bottom: 2px solid #3498db;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #34495e;
        }
        
        .form-control {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }
        
        .form-control:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
        }
        
        .form-group input[type="number"] {
            text-align: right;
        }
        
        .btn {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 24px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
        }
        
        .btn:hover {
            background-color: #2980b9;
        }
        
        .required::after {
            content: " *";
            color: #e74c3c;
        }
        
        .form-row {
            display: flex;
            gap: 20px;
        }
        
        .form-row .form-group {
            flex: 1;
        }
        
        .section-title {
            margin-top: 30px;
            margin-bottom: 20px;
            color: #3498db;
            border-bottom: 1px solid #eee;
            padding-bottom: 5px;
        }
        
        @media (max-width: 768px) {
            .form-row {
                flex-direction: column;
                gap: 0;
            }
        }
    </style>
</head>
<body>
        <%
        DAO_barang model = new DAO_barang();
        SimpleDateFormat sql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat show = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timestamps = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat sql2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String id="",nama_barang="",tanggal_pembelian="";
        String jumlah="",harga_satuan="",vendor="",tanggal_garansi="";
        String jenis_garansi="",penanggung_jawab="",total="";
        try{
            String pilihan = request.getParameter("submit");
            if(!pilihan.equals(null)){
                if(pilihan.equalsIgnoreCase("select") ){
                    ArrayList<barang> listCari = model.getCari(request.getParameter("id"));
                    for(barang item :listCari){
                        id=item.getId();
                        nama_barang=item.getNama_barang();
                        tanggal_pembelian=show.format(sql.parse(item.getTanggal_pembelian().toString()));
                        jumlah=String.valueOf(item.getJumlah());
                        harga_satuan=String.valueOf(item.getHarga_satuan());
                        vendor=item.getVendor();
                        tanggal_garansi=show.format(sql.parse(item.getTanggal_garansi().toString()));;
                        jenis_garansi=item.getJenis_garansi();
                        penanggung_jawab=item.getPenanggung_jawab();

                        DecimalFormat format = new DecimalFormat("###.##");
                        total=String.valueOf(format.format(item.getHarga_satuan()*(float)item.getJumlah()));
                    }
                }
                if(pilihan.equalsIgnoreCase("ubah")){

                }
                if(pilihan.equalsIgnoreCase("delete")){
                    model.delete(request.getParameter("id"));
                }
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ArrayList<barang> list = model.ALL();
        
    %>
    <div class="container">
        <h2>Input Data Barang</h2>
        <form action="BarangController" method="post" id="formBarang">
            <!-- Informasi Utama Barang -->
            <h4 class="section-title">Informasi Barang</h4>
            <div class="form-row">
                <div class="form-group">
                    <label for="id" class="required">ID Barang</label>
                    <input type="text" id="id" name="id" class="form-control" value="<%= id %>" required>
                </div>
                <div class="form-group">
                    <label for="nama_barang" class="required">Nama Barang</label>
                    <input type="text" id="nama_barang" name="nama_barang" class="form-control" value="<%= nama_barang %>" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="tanggal_pembelian" class="required">Tanggal Pembelian</label>
                    <input type="date" id="tanggal_pembelian" name="tanggal_pembelian" class="form-control" value="<%= tanggal_pembelian %>" required>
                </div>
                <div class="form-group">
                    <label for="jumlah" class="required">Jumlah</label>
                    <input type="number" id="jumlah" name="jumlah" class="form-control" required min="1" onchange="hitungTotal()" value="<%= jumlah %>">
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="harga_satuan" class="required">Harga Satuan (Rp)</label>
                    <input type="number" id="harga_satuan" name="harga_satuan" class="form-control" required min="0" onchange="hitungTotal()" value="<%= harga_satuan %>">
                </div>
                <div class="form-group">
                    <label for="total_harga">Total Harga (Rp)</label>
                    <input type="number" id="total_harga" name="total_harga" class="form-control" readonly value="<%=total%>">
                </div>
            </div>
            
            <!-- Informasi Vendor dan Penanggung Jawab -->
            <h4 class="section-title">Informasi Vendor</h4>
            <div class="form-group">
                <label for="vendor">Vendor</label>
                <input type="text" id="vendor" name="vendor" class="form-control" value="<%= vendor %>">
            </div>
            
            <div class="form-group">
                <label for="penanggung_jawab">Penanggung Jawab</label>
                <input type="text" id="penanggung_jawab" name="penanggung_jawab" class="form-control" value="<%= penanggung_jawab %>">
            </div>
            
            <!-- Informasi Garansi -->
            <h4 class="section-title">Informasi Garansi</h4>
            <div class="form-row">
                <div class="form-group">
                    <label for="tanggal_garansi">Tanggal Garansi</label>
                    <input type="date" id="tanggal_garansi" name="tanggal_garansi" class="form-control" value="<%= tanggal_garansi %>">
                </div>
                <div class="form-group">
                    <label for="jenis_garansi">Jenis Garansi</label>
                    <input type="text" id="jenis_garansi" name="jenis_garansi" class="form-control" value="<%= jenis_garansi %>">
                </div>
            </div>
            
            <div class="form-group">
                <button type="submit" class="btn">Simpan Data</button>
            </div>
        </form>
    </div>
        <table>
            <thead>
                <tr> 
                    <th>No.</th>
                    <th>Id</th>
                    <th>Nama Barang</th>
                    <th>Tanggal Pembelian</th>
                    <th>Jumlah</th>
                    <th>Harga Satuan</th>
                    <th>Vendor</th>
                    <th>Tanggal Garansi</th>
                    <th>Jenis Garansi</th>
                    <th>Penanggung Jawab</th>
                    <th>Created_at</th>
                </tr>
            </thead>
            <tbody>
                <%
                int i = 1;
                for(barang item:list){
                    %>
                    <tr>
                        <form method="POST" action="index.jsp" border="2" >
                            <td>
                                <%
                                    out.println(String.valueOf(i));;
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(item.getId());
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(item.getNama_barang());
                                %>
                            </td>
                            <td>
                                <%
                                    try{
                                        Date parse = sql.parse(item.getTanggal_pembelian().toString());
                                        out.println(show.format(parse));
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }                                    
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(String.valueOf(item.getJumlah()));
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(String.valueOf(item.getHarga_satuan()));
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(item.getVendor());
                                %>
                            </td>
                            <td>
                                <%
                                    try{
                                        Date parse = sql.parse(item.getTanggal_garansi().toString());
                                        out.println(show.format(parse));
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(item.getJenis_garansi());
                                %>
                            </td>
                            <td>
                                <%
                                    out.println(item.getPenanggung_jawab());
                                %>
                            </td>
                            <td>
                                <%
                                    try{
                                        Date parse = sql2.parse(item.getCreated_at().toString());
                                        out.println(timestamps.format(parse));
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                    
                                %>
                            </td>
                            <input type="hidden" value="<%= item.getId() %>" name="id">
                            <td>
                                <input type="submit" value="select" name="submit">
                            </td>
                            <td>
                                <input type="submit" value="ubah" name="submit">
                            </td>
                            <td>
                                <input type="submit" value="delete" name="submit">
                            </td>
                        </form>
                    </tr>
                    <%
                    i++;
                }
                %>
            </tbody>
        </table>
        <%
    %>
    <script>
        // Set default tanggal pembelian ke hari ini
        document.addEventListener('DOMContentLoaded', function() {
            const today = new Date().toISOString().split('T')[0];
            document.getElementById('tanggal_pembelian').value = today;
            
            // Set fokus ke ID Barang saat halaman dimuat
            document.getElementById('id').focus();
        });
        
        // Fungsi untuk menghitung total harga
        function hitungTotal() {
            const jumlah = document.getElementById('jumlah').value || 0;
            const hargaSatuan = document.getElementById('harga_satuan').value || 0;
            const totalHarga = jumlah * hargaSatuan;
            document.getElementById('total_harga').value = totalHarga;
        }
        
        // Validasi form sebelum submit
        document.getElementById('formBarang').addEventListener('submit', function(event) {
            const id = document.getElementById('id').value;
            const namaBarang = document.getElementById('nama_barang').value;
            
            if (id.trim() === '') {
                alert('ID Barang tidak boleh kosong!');
                event.preventDefault();
                return false;
            }
            
            if (namaBarang.trim() === '') {
                alert('Nama Barang tidak boleh kosong!');
                event.preventDefault();
                return false;
            }
            
            return true;
        });
    </script>
</body>
</html>