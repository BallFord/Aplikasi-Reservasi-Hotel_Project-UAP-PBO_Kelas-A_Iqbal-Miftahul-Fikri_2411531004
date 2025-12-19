package model;

public abstract class Kamar {
    protected double hargaDasar;
    private String nomorKamar;
    private boolean isTersedia;

    public Kamar(String nomorKamar, double hargaDasar) {
        this.nomorKamar = nomorKamar;
        this.hargaDasar = hargaDasar;
        this.isTersedia = true;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public boolean isTersedia() {
        return isTersedia;
    }

    public void setTersedia(boolean tersedia) {
        isTersedia = tersedia;
    }

    public abstract double hitungHarga();
}