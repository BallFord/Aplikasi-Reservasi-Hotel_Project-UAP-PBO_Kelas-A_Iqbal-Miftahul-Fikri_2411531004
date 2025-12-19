package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Kamar;
import model.Reservasi;
import model.Tamu;
import service.HotelService;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textNama;
    private JTextField textKTP;
    private JTextField textDurasi;
    private JComboBox<String> comboKamar;
    private JTextArea textAreaOutput;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        setTitle("Aplikasi Reservasi Hotel - PBO Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblJudul = new JLabel("FORM RESERVASI HOTEL");
        lblJudul.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblJudul.setBounds(200, 11, 250, 20);
        contentPane.add(lblJudul);

        JLabel lblNama = new JLabel("Nama Tamu:");
        lblNama.setBounds(30, 60, 100, 14);
        contentPane.add(lblNama);

        textNama = new JTextField();
        textNama.setBounds(140, 57, 200, 20);
        contentPane.add(textNama);
        textNama.setColumns(10);

        JLabel lblKTP = new JLabel("Nomor KTP:");
        lblKTP.setBounds(30, 90, 100, 14);
        contentPane.add(lblKTP);

        textKTP = new JTextField();
        textKTP.setBounds(140, 87, 200, 20);
        contentPane.add(textKTP);
        textKTP.setColumns(10);

        JLabel lblKamar = new JLabel("Pilih Kamar:");
        lblKamar.setBounds(30, 120, 100, 14);
        contentPane.add(lblKamar);

        comboKamar = new JComboBox<>();
        comboKamar.setBounds(140, 117, 200, 22);
        contentPane.add(comboKamar);

        loadDataKamar();

        JLabel lblDurasi = new JLabel("Durasi (Malam):");
        lblDurasi.setBounds(30, 150, 100, 14);
        contentPane.add(lblDurasi);

        textDurasi = new JTextField();
        textDurasi.setBounds(140, 147, 86, 20);
        contentPane.add(textDurasi);
        textDurasi.setColumns(10);

        JCheckBox chkBreakfast = new JCheckBox("Tambah Breakfast (Rp 50.000/mlm)");
        chkBreakfast.setBounds(140, 180, 250, 23);
        contentPane.add(chkBreakfast);

        JButton btnSimpan = new JButton("SIMPAN BARU");
        btnSimpan.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSimpan.setBounds(30, 220, 130, 30);
        contentPane.add(btnSimpan);

        JButton btnEdit = new JButton("EDIT DATA");
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEdit.setBounds(170, 220, 120, 30);
        contentPane.add(btnEdit);

        JButton btnHistory = new JButton("LIHAT HISTORY");
        btnHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnHistory.setBounds(300, 220, 140, 30);
        contentPane.add(btnHistory);

        JButton btnHapus = new JButton("HAPUS DATA");
        btnHapus.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnHapus.setBounds(450, 220, 120, 30);
        contentPane.add(btnHapus);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 270, 560, 160);
        contentPane.add(scrollPane);

        textAreaOutput = new JTextArea();
        scrollPane.setViewportView(textAreaOutput);
        textAreaOutput.setEditable(false);

        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prosesReservasi(chkBreakfast.isSelected(), 0);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Masukkan ID Reservasi yang akan diedit:");
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        prosesReservasi(chkBreakfast.isSelected(), id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID harus angka!");
                    }
                }
            }
        });

        btnHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String history = HotelService.getInstance().getHistoryReservasi();
                textAreaOutput.setText("=== RIWAYAT RESERVASI ===\n\n" + history);
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Masukkan ID Reservasi yang akan dihapus:");
                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        int confirm = JOptionPane.showConfirmDialog(null, "Yakin hapus ID " + id + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            HotelService.getInstance().hapusReservasi(id);
                            String history = HotelService.getInstance().getHistoryReservasi();
                            textAreaOutput.setText("=== DATA BERHASIL DIHAPUS ===\n\n" + history);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID harus angka!");
                    }
                }
            }
        });
    }

    private void loadDataKamar() {
        List<Kamar> list = HotelService.getInstance().getKamarTersedia();
        comboKamar.removeAllItems();
        for (Kamar k : list) {
            comboKamar.addItem(k.getNomorKamar());
        }
    }

    private void prosesReservasi(boolean isBreakfast, int idEdit) {
        try {
            String nama = textNama.getText();
            String ktp = textKTP.getText();
            String durasiStr = textDurasi.getText();
            String noKamar = (String) comboKamar.getSelectedItem();

            if (nama.isEmpty() || ktp.isEmpty() || durasiStr.isEmpty() || noKamar == null) {
                JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int durasi = Integer.parseInt(durasiStr);
            Tamu tamu = new Tamu(nama, ktp);

            Kamar kamarDipilih = null;
            for (Kamar k : HotelService.getInstance().getKamarTersedia()) {
                if (k.getNomorKamar().equals(noKamar)) {
                    kamarDipilih = k;
                    break;
                }
            }

            Reservasi.Builder builder = new Reservasi.Builder()
                    .setTamu(tamu)
                    .setKamar(kamarDipilih)
                    .setDurasi(durasi);

            if (isBreakfast) {
                builder.withBreakfast();
            }

            Reservasi reservasi = builder.build();

            if (idEdit == 0) {
                HotelService.getInstance().buatReservasi(reservasi);
                JOptionPane.showMessageDialog(this, "Reservasi BARU Berhasil Disimpan!");
                tampilkanInvoice(reservasi, nama, noKamar, durasi, "BARU");
            } else {
                HotelService.getInstance().editReservasi(idEdit, reservasi);
                JOptionPane.showMessageDialog(this, "Data ID " + idEdit + " Berhasil Di-Update!");
                tampilkanInvoice(reservasi, nama, noKamar, durasi, "UPDATE");
            }

            resetForm();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Durasi harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanInvoice(Reservasi r, String nama, String kamar, int durasi, String status) {
        String statusBreakfast = r.isWithBreakfast() ? "Ya" : "Tidak";

        textAreaOutput.setText("");
        textAreaOutput.append("=== BUKTI RESERVASI (" + status + ") ===\n");
        textAreaOutput.append("Nama Tamu\t: " + nama + "\n");
        textAreaOutput.append("Nomor Kamar\t: " + kamar + "\n");
        textAreaOutput.append("Durasi Menginap\t: " + durasi + " Malam\n");
        textAreaOutput.append("Breakfast\t: " + statusBreakfast + "\n");
        textAreaOutput.append("Status\t\t: BERHASIL\n");
        textAreaOutput.append("---------------------------------------------\n");
        r.cetakInvoice();
    }

    private void resetForm() {
        textNama.setText("");
        textKTP.setText("");
        textDurasi.setText("");
        comboKamar.setSelectedIndex(0);
        
    }
}