package latihan.rico.com.bpptpromosi.ModelLaporanSektor;

public class ModelListSektorTriwulan {
    String triwulan;
    String nominaltriwulan;

    public ModelListSektorTriwulan(String triwulan, String nominaltriwulan) {
        this.triwulan = triwulan;
        this.nominaltriwulan = nominaltriwulan;
    }

    public String getTriwulan() {
        return triwulan;
    }

    public void setTriwulan(String triwulan) {
        this.triwulan = triwulan;
    }

    public String getNominaltriwulan() {
        return nominaltriwulan;
    }

    public void setNominaltriwulan(String nominaltriwulan) {
        this.nominaltriwulan = nominaltriwulan;
    }
}
