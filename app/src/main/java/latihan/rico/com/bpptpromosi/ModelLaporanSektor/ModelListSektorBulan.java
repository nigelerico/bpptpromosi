package latihan.rico.com.bpptpromosi.ModelLaporanSektor;

public class ModelListSektorBulan {
    String bulan;
    Double nominal;

    public ModelListSektorBulan(String bulan, Double nominal) {
        this.bulan = bulan;
        this.nominal = nominal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }
}
