package latihan.rico.com.bpptpromosi.ModelLaporanSektor;

public class ModelListSektorTahun {
    int tahun;
    Double nominal;

    public ModelListSektorTahun(int tahun, Double nominal) {
        this.tahun = tahun;
        this.nominal = nominal;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }
}
