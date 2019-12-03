package latihan.rico.com.bpptpromosi.ModelLaporanSektor;

public class ModelListSektorTahun {
    int tahun;
    String nominal;

    public ModelListSektorTahun(int tahun, String nominal) {
        this.tahun = tahun;
        this.nominal = nominal;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
}
