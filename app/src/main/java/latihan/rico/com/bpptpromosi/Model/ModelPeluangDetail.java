package latihan.rico.com.bpptpromosi.Model;

import java.util.ArrayList;

public class ModelPeluangDetail{
    public String id;
    public String nama_sektor;
    public String alamat_sektor;
    public String telepon_sektor;
    public String nama_pengelola;
    public String deskripsi;
    public String lokasi;
    public String latitude;
    public String longitude;
    public String luas_tanah;
    public String status_kepemilikan;
    public String estimasi_biaya;
    public String skema_kerjasama;
    public String email;
    public String website;
    public String status;
    public String created_at;
    public String updated_at;
    public String created_by;
    public ArrayList<String> images;

    public ModelPeluangDetail(String id, String nama_sektor, String alamat_sektor, String telepon_sektor, String nama_pengelola, String deskripsi, String lokasi, String latitude, String longitude, String luas_tanah, String status_kepemilikan, String estimasi_biaya, String skema_kerjasama, String email, String website, String status, String created_at, String updated_at, String created_by, ArrayList<String> images) {
        this.id = id;
        this.nama_sektor = nama_sektor;
        this.alamat_sektor = alamat_sektor;
        this.telepon_sektor = telepon_sektor;
        this.nama_pengelola = nama_pengelola;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.luas_tanah = luas_tanah;
        this.status_kepemilikan = status_kepemilikan;
        this.estimasi_biaya = estimasi_biaya;
        this.skema_kerjasama = skema_kerjasama;
        this.email = email;
        this.website = website;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.images = images;
    }

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

    public String getLuas_tanah() {
        return luas_tanah;
    }

    public String getStatus_kepemilikan() {
        return status_kepemilikan;
    }

    public String getEstimasi_biaya() {
        return estimasi_biaya;
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

    public ArrayList<String> getImages() {
        return images;
    }

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

    public void setLuas_tanah(String luas_tanah) {
        this.luas_tanah = luas_tanah;
    }

    public void setStatus_kepemilikan(String status_kepemilikan) {
        this.status_kepemilikan = status_kepemilikan;
    }

    public void setEstimasi_biaya(String estimasi_biaya) {
        this.estimasi_biaya = estimasi_biaya;
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

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}

