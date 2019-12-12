package latihan.rico.com.bpptpromosi.ModelLaporanSektor;

public class ModelListSektorTriwulan {
    String triwulan;
    Double nominaltriwulan;

    public ModelListSektorTriwulan(String triwulan, Double nominaltriwulan) {
        this.triwulan = triwulan;
        this.nominaltriwulan = nominaltriwulan;
    }

    public String getTriwulan() {
        return triwulan;
    }

    public void setTriwulan(String triwulan) {
        this.triwulan = triwulan;
    }

    public Double getNominaltriwulan() {
        return nominaltriwulan;
    }

    public void setNominaltriwulan(Double nominaltriwulan) {
        this.nominaltriwulan = nominaltriwulan;
    }
}
