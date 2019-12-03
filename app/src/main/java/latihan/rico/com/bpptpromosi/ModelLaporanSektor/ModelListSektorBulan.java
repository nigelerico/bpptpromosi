package latihan.rico.com.bpptpromosi.ModelLaporanSektor;

public class ModelListSektorBulan {
    String bulan;
    String nominal;

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public ModelListSektorBulan(String bulan, String nominal) {
        this.bulan = bulan;
        this.nominal = nominal;
    }
}
