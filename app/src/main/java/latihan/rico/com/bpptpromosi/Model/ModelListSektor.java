package latihan.rico.com.bpptpromosi.Model;

public class ModelListSektor {
    public ModelListSektor(int id, int id_sektor, int id_subsektor, String nama_sektor, String alamat_sektor, String telepon_sektor, String nama_pengelola, String deskripsi, String statusVerifikasi, String value) {
        this.id = id;
        this.id_sektor = id_sektor;
        this.id_subsektor = id_subsektor;
        this.nama_sektor = nama_sektor;
        this.alamat_sektor = alamat_sektor;
        this.telepon_sektor = telepon_sektor;
        this.nama_pengelola = nama_pengelola;
        this.deskripsi = deskripsi;
        this.statusVerifikasi = statusVerifikasi;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sektor() {
        return id_sektor;
    }

    public void setId_sektor(int id_sektor) {
        this.id_sektor = id_sektor;
    }

    public int getId_subsektor() {
        return id_subsektor;
    }

    public void setId_subsektor(int id_subsektor) {
        this.id_subsektor = id_subsektor;
    }

    public String getNama_sektor() {
        return nama_sektor;
    }

    public void setNama_sektor(String nama_sektor) {
        this.nama_sektor = nama_sektor;
    }

    public String getAlamat_sektor() {
        return alamat_sektor;
    }

    public void setAlamat_sektor(String alamat_sektor) {
        this.alamat_sektor = alamat_sektor;
    }

    public String getTelepon_sektor() {
        return telepon_sektor;
    }

    public void setTelepon_sektor(String telepon_sektor) {
        this.telepon_sektor = telepon_sektor;
    }

    public String getNama_pengelola() {
        return nama_pengelola;
    }

    public void setNama_pengelola(String nama_pengelola) {
        this.nama_pengelola = nama_pengelola;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public void setStatusVerifikasi(String statusVerifikasi) {
        this.statusVerifikasi = statusVerifikasi;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int id;
    public int id_sektor;
    public int id_subsektor;
    public String nama_sektor;
    public String alamat_sektor;
    public String telepon_sektor;
    public String nama_pengelola;
    public String deskripsi;
    public String statusVerifikasi;
    public String value;
}