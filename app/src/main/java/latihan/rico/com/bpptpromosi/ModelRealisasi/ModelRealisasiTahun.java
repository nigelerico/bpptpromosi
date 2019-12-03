package latihan.rico.com.bpptpromosi.ModelRealisasi;

public class ModelRealisasiTahun {
    int id;
    String tahun;

    public ModelRealisasiTahun(int id, String tahun) {
        this.id = id;
        this.tahun = tahun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
