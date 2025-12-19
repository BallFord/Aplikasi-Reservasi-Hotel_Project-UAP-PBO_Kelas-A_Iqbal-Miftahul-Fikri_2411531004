package service;

import java.util.ArrayList;
import java.util.List;
import dao.KamarDAO;
import dao.ReservasiDAO;
import model.Kamar;
import model.Reservasi;

public class HotelService {
    private static HotelService instance;
    private KamarDAO kamarDAO;
    private ReservasiDAO reservasiDAO;
    private List<Reservasi> listReservasi;

    private HotelService() {
        kamarDAO = new KamarDAO();
        reservasiDAO = new ReservasiDAO();
        listReservasi = new ArrayList<>();
    }

    public static HotelService getInstance() {
        if (instance == null) {
            instance = new HotelService();
        }
        return instance;
    }

    public List<Kamar> getKamarTersedia() {
        List<Kamar> allKamar = kamarDAO.getAllKamar();
        List<Kamar> tersedia = new ArrayList<>();
        for (Kamar k : allKamar) {
            if (k.isTersedia()) {
                tersedia.add(k);
            }
        }
        return tersedia;
    }

    public void buatReservasi(Reservasi r) {
        listReservasi.add(r);
        reservasiDAO.insertReservasi(
            r.getTamu().getNama(),
            r.getTamu().getNoKTP(),
            r.getKamar().getNomorKamar(),
            r.getDurasi(),
            r.isWithBreakfast(),
            r.getTotalBiaya()
        );
    }

    public String getHistoryReservasi() {
        return reservasiDAO.getAllReservasi();
    }

    public void hapusReservasi(int id) {
        reservasiDAO.deleteReservasi(id);
    }

    public void editReservasi(int id, Reservasi r) {
        reservasiDAO.updateReservasi(
            id,
            r.getTamu().getNama(),
            r.getTamu().getNoKTP(),
            r.getKamar().getNomorKamar(),
            r.getDurasi(),
            r.isWithBreakfast(),
            r.getTotalBiaya()
        );
    }
}