<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="validasiInput.barang"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DecimalFormat formatter = new DecimalFormat("#,###");
    String message = (String) request.getAttribute("message");
%>
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
    <div class="container">
        <h2>Input Data Barang</h2>
        <form action="BarangController" method="post" id="formBarang">
            <input type="hidden" name="aksi" id="aksi" value="insert" />
            <div class="form-row">
                <div class="form-group">
                    <label for="id" class="required">ID Barang</label>
                    <input type="text" id="id" name="id" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="nama_barang" class="required">Nama Barang</label>
                    <input type="text" id="nama_barang" name="nama_barang" class="form-control" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="tanggal_pembelian" class="required">Tanggal Pembelian</label>
                    <input type="date" id="tanggal_pembelian" name="tanggal_pembelian" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="jumlah" class="required">Jumlah</label>
                    <input type="number" id="jumlah" name="jumlah" class="form-control" required min="1" onchange="hitungTotal()">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="harga_satuan" class="required">Harga Satuan (Rp)</label>
                    <input type="number" id="harga_satuan" name="harga_satuan" class="form-control" required min="0" onchange="hitungTotal()">
                </div>
                <div class="form-group">
                    <label for="total_harga">Total Harga (Rp)</label>
                    <input type="number" id="total_harga" name="total_harga" class="form-control" readonly>
                </div>
            </div>

            <div class="form-group">
                <label for="vendor">Vendor</label>
                <input type="text" id="vendor" name="vendor" class="form-control">
            </div>

            <div class="form-group">
                <label for="penanggung_jawab">Penanggung Jawab</label>
                <input type="text" id="penanggung_jawab" name="penanggung_jawab" class="form-control">
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="tanggal_garansi">Tanggal Garansi</label>
                    <input type="date" id="tanggal_garansi" name="tanggal_garansi" class="form-control">
                </div>
                <div class="form-group">
                    <label for="jenis_garansi">Jenis Garansi</label>
                    <input type="text" id="jenis_garansi" name="jenis_garansi" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <button type="submit" class="btn">Simpan Data</button>
            </div>
        </form>

        <hr>

        <button type="button" onclick="window.location.href='BarangController'">Lihat Semua Data</button>

        <h3>Daftar Barang</h3>
        <table border="1" cellpadding="5" cellspacing="0" width="100%">
            <thead>
                <tr style="background-color:#3498db; color:white;">
                    <th>ID</th>
                    <th>Nama Barang</th>
                    <th>Tanggal Pembelian</th>
                    <th>Jumlah</th>
                    <th>Harga Satuan (Rp)</th>
                    <th>Vendor</th>
                    <th>Tanggal Garansi</th>
                    <th>Jenis Garansi</th>
                    <th>Penanggung Jawab</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
            <%
                ArrayList<barang> list = (ArrayList<barang>) request.getAttribute("dataBarang");
                if (list != null && !list.isEmpty()) {
                    for (barang b : list) {
            %>
                <tr>
                    <td><%= b.getId() %></td>
                    <td><%= b.getNama_barang() %></td>
                    <td><%= b.getTanggal_pembelian() %></td>
                    <td style="text-align:right;"><%= b.getJumlah() %></td>
                    <td style="text-align:right;">Rp <%= formatter.format(b.getHarga_satuan()) %></td>
                    <td><%= b.getVendor() != null ? b.getVendor() : "-" %></td>
                    <td><%= b.getTanggal_garansi() != null ? b.getTanggal_garansi() : "-" %></td>
                    <td><%= b.getJenis_garansi() != null ? b.getJenis_garansi() : "-" %></td>
                    <td><%= b.getPenanggung_jawab() != null ? b.getPenanggung_jawab() : "-" %></td>
                    <td>
                        <button onclick="editBarang('<%=b.getId()%>', '<%=b.getNama_barang()%>', '<%=b.getTanggal_pembelian()%>', '<%=b.getJumlah()%>', '<%=b.getHarga_satuan()%>', '<%=b.getVendor()%>', '<%=b.getTanggal_garansi()%>', '<%=b.getJenis_garansi()%>', '<%=b.getPenanggung_jawab()%>')">Edit</button>
                        <button onclick="deleteBarang('<%=b.getId()%>')">Hapus</button>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="10" style="text-align:center;">Data kosong</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>

<script>
    function hitungTotal() {
        const jumlah = parseFloat(document.getElementById('jumlah').value || 0);
        const hargaSatuan = parseFloat(document.getElementById('harga_satuan').value || 0);
        const totalHarga = jumlah * hargaSatuan;
        document.getElementById('total_harga').value = Math.round(totalHarga);
    }

    function editBarang(id, nama_barang, tanggal_pembelian, jumlah, harga_satuan, vendor, tanggal_garansi, jenis_garansi, penanggung_jawab) {
        document.getElementById('id').value = id;
        document.getElementById('id').readOnly = true;
        document.getElementById('nama_barang').value = nama_barang;
        document.getElementById('tanggal_pembelian').value = tanggal_pembelian ? tanggal_pembelian.split(' ')[0] : "";
        document.getElementById('jumlah').value = jumlah;
        document.getElementById('harga_satuan').value = harga_satuan;
        document.getElementById('vendor').value = vendor || "";
        document.getElementById('tanggal_garansi').value = tanggal_garansi && tanggal_garansi !== "null" ? tanggal_garansi.split(' ')[0] : "";
        document.getElementById('jenis_garansi').value = jenis_garansi || "";
        const radios = document.getElementsByName('jenis_garansi');
        radios.forEach(r => r.checked = (r.value === jenis_garansi));
        document.getElementById('penanggung_jawab').value = penanggung_jawab || "";
        hitungTotal();
        document.getElementById('aksi').value = 'update';
    }

    function deleteBarang(id) {
        if (confirm("Yakin ingin hapus data ID " + id + "?")) {
            const form = document.createElement("form");
            form.method = "post";
            form.action = "BarangController";

            const aksiInput = document.createElement("input");
            aksiInput.type = "hidden";
            aksiInput.name = "aksi";
            aksiInput.value = "delete";

            const idInput = document.createElement("input");
            idInput.type = "hidden";
            idInput.name = "id";
            idInput.value = id;

            form.appendChild(aksiInput);
            form.appendChild(idInput);

            document.body.appendChild(form);
            form.submit();
        }
    }
</script>
</body>
</html>