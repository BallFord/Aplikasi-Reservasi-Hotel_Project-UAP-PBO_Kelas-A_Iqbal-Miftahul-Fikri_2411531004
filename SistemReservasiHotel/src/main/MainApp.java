package main;

import model.*;
import service.HotelService;

public class MainApp {
    public static void main(String[] args) {
        HotelService service = HotelService.getInstance();

        System.out.println("List Kamar Tersedia:");
        for (Kamar k : service.getKamarTersedia()) {
            System.out.println("- " + k.getNomorKamar() + " (Rp " + k.hitungHarga() + ")");
        }

        Tamu tamu1 = new Tamu("Iqbal Miftahul Fikri", "12345678");
        Kamar kamarDipilih = service.getKamarTersedia().get(0);

        try {
            System.out.println("\nMembuat Reservasi...");

            Reservasi bookingBaru = new Reservasi.Builder()
                    .setTamu(tamu1)
                    .setKamar(kamarDipilih)
                    .setDurasi(3)
                    .withBreakfast()
                    .build();

            service.buatReservasi(bookingBaru);
            bookingBaru.cetakInvoice();

        } catch (Exception e) {
            System.err.println("Terjadi Error: " + e.getMessage());
        }
    }
}