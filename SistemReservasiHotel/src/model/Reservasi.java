package model;

public class Reservasi {
    private Tamu tamu;
    private Kamar kamar;
    private int durasiMalam;
    private boolean withBreakfast;
    private double totalBiaya;

    private Reservasi(Builder builder) {
        this.tamu = builder.tamu;
        this.kamar = builder.kamar;
        this.durasiMalam = builder.durasiMalam;
        this.withBreakfast = builder.withBreakfast;
        this.totalBiaya = hitungTotal();
    }

    private double hitungTotal() {
        double harga = kamar.hitungHarga() * durasiMalam;
        if (withBreakfast) {
            harga += (50000 * durasiMalam);
        }
        return harga;
    }

    public void cetakInvoice() {
        System.out.println("=== DETAIL PESANAN ===");
        System.out.println("Tamu   : " + tamu.getNama());
        System.out.println("Kamar  : " + kamar.getNomorKamar());
        System.out.println("Durasi : " + durasiMalam + " Malam");
        System.out.println("Total  : Rp " + (long) totalBiaya);
        System.out.println("=====================");
    }

    public Tamu getTamu() {
        return tamu;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public int getDurasi() {
        return durasiMalam;
    }

    public boolean isWithBreakfast() {
        return withBreakfast;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public static class Builder {
        private Tamu tamu;
        private Kamar kamar;
        private int durasiMalam;
        private boolean withBreakfast = false;

        public Builder setTamu(Tamu tamu) {
            this.tamu = tamu;
            return this;
        }

        public Builder setKamar(Kamar kamar) {
            this.kamar = kamar;
            return this;
        }

        public Builder setDurasi(int durasi) {
            this.durasiMalam = durasi;
            return this;
        }

        public Builder withBreakfast() {
            this.withBreakfast = true;
            return this;
        }

        public Reservasi build() {
            if (tamu == null || kamar == null || durasiMalam <= 0) {
                throw new IllegalStateException("Data Reservasi belum lengkap!");
            }
            return new Reservasi(this);
        }
    }
}