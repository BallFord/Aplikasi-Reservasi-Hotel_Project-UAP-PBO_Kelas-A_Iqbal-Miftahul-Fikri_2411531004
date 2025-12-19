package dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Kamar;
import model.KamarStandard;
import model.KamarVIP;

public class KamarDAO {
    
    public List<Kamar> getAllKamar() {
        List<Kamar> listKamar = new ArrayList<>();
        String query = "SELECT * FROM kamar";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String no = rs.getString("nomor_kamar");
                String tipe = rs.getString("tipe_kamar");
                boolean tersedia = rs.getInt("is_tersedia") == 1;

                Kamar k;
                if (tipe != null && tipe.equalsIgnoreCase("VIP")) {
                    k = new KamarVIP(no);
                } else {
                    k = new KamarStandard(no);
                }
                k.setTersedia(tersedia);
                listKamar.add(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKamar;
    }
}