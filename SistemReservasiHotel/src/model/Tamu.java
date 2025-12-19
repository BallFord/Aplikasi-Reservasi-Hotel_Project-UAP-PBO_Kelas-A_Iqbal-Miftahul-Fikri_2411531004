package model;

public class Tamu {
    private String nama;
    private String noKTP;

    public Tamu(String nama, String noKTP) {
        this.nama = nama;
        this.noKTP = noKTP;
    }

    public String getNama() {
        return nama;
    }

    public String getNoKTP() {
        return noKTP;
    }
}