package latihan.rico.com.bpptpromosi.Model;

public class ModelEventImage {
    public int id;
    public String gambar;

    public ModelEventImage(int id, String gambar) {
        this.id = id;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
