package model;

public class KamarVIP extends Kamar {

    public KamarVIP(String nomor) {
        super(nomor, 500000);
    }

    @Override
    public double hitungHarga() {
        return hargaDasar + (hargaDasar * 0.1);
    }
}