package latihan.rico.com.bpptpromosi.ModelRealisasi;

public class ModelRealisasiBulan {

    int id;
    int id_tahun;
    String bulan;
    Double value;
    String bulanfix;

    public ModelRealisasiBulan(int id, int id_tahun, String bulan, Double value, String bulanfix) {
        this.id = id;
        this.id_tahun = id_tahun;
        this.bulan = bulan;
        this.value = value;
        this.bulanfix = bulanfix;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tahun() {
        return id_tahun;
    }

    public void setId_tahun(int id_tahun) {
        this.id_tahun = id_tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getBulanfix() {
        return bulanfix;
    }

    public void setBulanfix(String bulanfix) {
        this.bulanfix = bulanfix;
    }
}
