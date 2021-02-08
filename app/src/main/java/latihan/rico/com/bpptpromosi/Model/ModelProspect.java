package latihan.rico.com.bpptpromosi.Model;

public class ModelProspect {

    public String id;
    public String nama;
    public String foto;
    public String deskripsi;
    public String url;
    public String status;
    public String created_at;
    public String updated_at;
    public String created_by;

    public ModelProspect(String id, String nama, String foto, String deskripsi, String url, String status, String created_at, String updated_at, String created_by) {
        this.id = id;
        this.nama = nama;
        this.foto = foto;
        this.deskripsi = deskripsi;
        this.url = url;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
    }

    public ModelProspect() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
