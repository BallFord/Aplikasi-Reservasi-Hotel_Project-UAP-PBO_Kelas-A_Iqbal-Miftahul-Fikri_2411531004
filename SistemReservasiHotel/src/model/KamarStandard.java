package model;

public class KamarStandard extends Kamar {

    public KamarStandard(String nomor) {
        super(nomor, 200000);
    }

    @Override
    public double hitungHarga() {
        return hargaDasar;
    }
}