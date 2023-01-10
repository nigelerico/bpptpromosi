package latihan.rico.com.bpptpromosi.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelIipro implements Parcelable {
    private String id;
    private String nama_sektor;
    private String alamat_sektor;
    private String telepon_sektor;
    private String nama_pengelola;
    private String deskripsi;
    private String lokasi;
    private String latitude;
    private String longitude;
    private String nilai_investasi;
    private String analisa;
    private String kapasitas;
    private String total_area;
    private String pendukung;
    private String skema_kerjasama;
    private String email;
    private String website;
    private String status;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String image;

    public ModelIipro(String id, String nama_sektor, String alamat_sektor, String telepon_sektor, String nama_pengelola, String deskripsi, String lokasi, String latitude, String longitude, String nilai_investasi, String analisa, String kapasitas, String total_area, String pendukung, String skema_kerjasama, String email, String website, String status, String created_at, String updated_at, String created_by, String image) {
        this.id = id;
        this.nama_sektor = nama_sektor;
        this.alamat_sektor = alamat_sektor;
        this.telepon_sektor = telepon_sektor;
        this.nama_pengelola = nama_pengelola;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nilai_investasi = nilai_investasi;
        this.analisa = analisa;
        this.kapasitas = kapasitas;
        this.total_area = total_area;
        this.pendukung = pendukung;
        this.skema_kerjasama = skema_kerjasama;
        this.email = email;
        this.website = website;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.image = image;
    }

// Getter Methods

    protected ModelIipro(Parcel in) {
        id = in.readString();
        nama_sektor = in.readString();
        alamat_sektor = in.readString();
        telepon_sektor = in.readString();
        nama_pengelola = in.readString();
        deskripsi = in.readString();
        lokasi = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        nilai_investasi = in.readString();
        analisa = in.readString();
        kapasitas = in.readString();
        total_area = in.readString();
        pendukung = in.readString();
        skema_kerjasama = in.readString();
        email = in.readString();
        website = in.readString();
        status = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        created_by = in.readString();
        image = in.readString();
    }

    public static final Creator<ModelIipro> CREATOR = new Creator<ModelIipro>() {
        @Override
        public ModelIipro createFromParcel(Parcel in) {
            return new ModelIipro(in);
        }

        @Override
        public ModelIipro[] newArray(int size) {
            return new ModelIipro[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNama_sektor() {
        return nama_sektor;
    }

    public String getAlamat_sektor() {
        return alamat_sektor;
    }

    public String getTelepon_sektor() {
        return telepon_sektor;
    }

    public String getNama_pengelola() {
        return nama_pengelola;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNilai_investasi() {
        return nilai_investasi;
    }

    public String getAnalisa() {
        return analisa;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public String getTotal_area() {
        return total_area;
    }

    public String getPendukung() {
        return pendukung;
    }

    public String getSkema_kerjasama() {
        return skema_kerjasama;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getImage() {
        return image;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setNama_sektor(String nama_sektor) {
        this.nama_sektor = nama_sektor;
    }

    public void setAlamat_sektor(String alamat_sektor) {
        this.alamat_sektor = alamat_sektor;
    }

    public void setTelepon_sektor(String telepon_sektor) {
        this.telepon_sektor = telepon_sektor;
    }

    public void setNama_pengelola(String nama_pengelola) {
        this.nama_pengelola = nama_pengelola;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setNilai_investasi(String nilai_investasi) {
        this.nilai_investasi = nilai_investasi;
    }

    public void setAnalisa(String analisa) {
        this.analisa = analisa;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setTotal_area(String total_area) {
        this.total_area = total_area;
    }

    public void setPendukung(String pendukung) {
        this.pendukung = pendukung;
    }

    public void setSkema_kerjasama(String skema_kerjasama) {
        this.skema_kerjasama = skema_kerjasama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama_sektor);
        dest.writeString(alamat_sektor);
        dest.writeString(telepon_sektor);
        dest.writeString(nama_pengelola);
        dest.writeString(deskripsi);
        dest.writeString(lokasi);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(nilai_investasi);
        dest.writeString(analisa);
        dest.writeString(kapasitas);
        dest.writeString(total_area);
        dest.writeString(pendukung);
        dest.writeString(skema_kerjasama);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeString(status);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(created_by);
        dest.writeString(image);
    }
}