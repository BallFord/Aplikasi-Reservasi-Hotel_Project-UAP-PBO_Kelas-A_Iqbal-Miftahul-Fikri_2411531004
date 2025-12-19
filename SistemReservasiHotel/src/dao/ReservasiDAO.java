package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservasiDAO {

    public void insertReservasi(String nama, String ktp, String kamar, int durasi, boolean breakfast, double total) {
        String sql = "INSERT INTO reservasi (nama_tamu, no_ktp, nomor_kamar, durasi, pake_breakfast, total_harga) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, ktp);
            ps.setString(3, kamar);
            ps.setInt(4, durasi);
            ps.setBoolean(5, breakfast);
            ps.setDouble(6, total);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAllReservasi() {
        StringBuilder sb = new StringBuilder();
        String query = "SELECT * FROM reservasi ORDER BY id DESC";

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                boolean isBreakfast = rs.getBoolean("pake_breakfast");
                String statusBreakfast = isBreakfast ? "Ya" : "Tidak";

                sb.append("ID: ").append(rs.getInt("id")).append("\n");
                sb.append("Nama: ").append(rs.getString("nama_tamu")).append("\n");
                sb.append("Kamar: ").append(rs.getString("nomor_kamar")).append("\n");
                sb.append("Durasi: ").append(rs.getInt("durasi")).append(" Malam\n");
                sb.append("Breakfast: ").append(statusBreakfast).append("\n");
                sb.append("Total: Rp ").append((long) rs.getDouble("total_harga")).append("\n");
                sb.append("-----------------------------------\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void deleteReservasi(int id) {
        String sql = "DELETE FROM reservasi WHERE id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateReservasi(int id, String nama, String ktp, String kamar, int durasi, boolean breakfast, double total) {
        String sql = "UPDATE reservasi SET nama_tamu=?, no_ktp=?, nomor_kamar=?, durasi=?, pake_breakfast=?, total_harga=? WHERE id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, ktp);
            ps.setString(3, kamar);
            ps.setInt(4, durasi);
            ps.setBoolean(5, breakfast);
            ps.setDouble(6, total);
            ps.setInt(7, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}