package latihan.rico.com.bpptpromosi.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelLahan implements Parcelable {
    private String id;
    private String nama_proyek;
    private String luas;
    private String status_lahan;
    private String lokasi;
    private String latitude;
    private String longitude;
    private String nama_pemilik;
    private String status_kontrak;
    private String kota;
    private String kecamatan;
    private String kelurahan;
    private String zonasi;
    private String keterangan;
    private String status;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String image;

    public ModelLahan(String id, String nama_proyek, String luas, String status_lahan, String lokasi, String latitude, String longitude, String nama_pemilik, String status_kontrak, String kota, String kecamatan, String kelurahan, String zonasi, String keterangan, String status, String created_at, String updated_at, String created_by, String image) {
        this.id = id;
        this.nama_proyek = nama_proyek;
        this.luas = luas;
        this.status_lahan = status_lahan;
        this.lokasi = lokasi;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nama_pemilik = nama_pemilik;
        this.status_kontrak = status_kontrak;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.zonasi = zonasi;
        this.keterangan = keterangan;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.image = image;
    }


    // Getter Methods

    protected ModelLahan(Parcel in) {
        id = in.readString();
        nama_proyek = in.readString();
        luas = in.readString();
        status_lahan = in.readString();
        lokasi = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        nama_pemilik = in.readString();
        status_kontrak = in.readString();
        kota = in.readString();
        kecamatan = in.readString();
        kelurahan = in.readString();
        zonasi = in.readString();
        keterangan = in.readString();
        status = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        created_by = in.readString();
        image = in.readString();
    }

    public static final Creator<ModelLahan> CREATOR = new Creator<ModelLahan>() {
        @Override
        public ModelLahan createFromParcel(Parcel in) {
            return new ModelLahan(in);
        }

        @Override
        public ModelLahan[] newArray(int size) {
            return new ModelLahan[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNama_proyek() {
        return nama_proyek;
    }

    public String getLuas() {
        return luas;
    }

    public String getStatus_lahan() {
        return status_lahan;
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

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public String getStatus_kontrak() {
        return status_kontrak;
    }

    public String getKota() {
        return kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getZonasi() {
        return zonasi;
    }

    public String getKeterangan() {
        return keterangan;
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

    public void setNama_proyek(String nama_proyek) {
        this.nama_proyek = nama_proyek;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public void setStatus_lahan(String status_lahan) {
        this.status_lahan = status_lahan;
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

    public void setNama_pemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
    }

    public void setStatus_kontrak(String status_kontrak) {
        this.status_kontrak = status_kontrak;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public void setZonasi(String zonasi) {
        this.zonasi = zonasi;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        dest.writeString(nama_proyek);
        dest.writeString(luas);
        dest.writeString(status_lahan);
        dest.writeString(lokasi);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(nama_pemilik);
        dest.writeString(status_kontrak);
        dest.writeString(kota);
        dest.writeString(kecamatan);
        dest.writeString(kelurahan);
        dest.writeString(zonasi);
        dest.writeString(keterangan);
        dest.writeString(status);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(created_by);
        dest.writeString(image);
    }
}
