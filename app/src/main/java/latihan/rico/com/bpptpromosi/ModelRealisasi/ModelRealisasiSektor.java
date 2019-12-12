package latihan.rico.com.bpptpromosi.ModelRealisasi;

public class ModelRealisasiSektor {
    int id;
    int id_bulan;
    int id_sektor;
    String unitusaha;
    Double investasi;
    String tenaga_kerja;
    String nama_sektor;
    String logo;

    public ModelRealisasiSektor(int id, int id_bulan, int id_sektor, String unitusaha, Double investasi, String tenaga_kerja, String nama_sektor, String logo) {
        this.id = id;
        this.id_bulan = id_bulan;
        this.id_sektor = id_sektor;
        this.unitusaha = unitusaha;
        this.investasi = investasi;
        this.tenaga_kerja = tenaga_kerja;
        this.nama_sektor = nama_sektor;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_bulan() {
        return id_bulan;
    }

    public void setId_bulan(int id_bulan) {
        this.id_bulan = id_bulan;
    }

    public int getId_sektor() {
        return id_sektor;
    }

    public void setId_sektor(int id_sektor) {
        this.id_sektor = id_sektor;
    }

    public String getUnitusaha() {
        return unitusaha;
    }

    public void setUnitusaha(String unitusaha) {
        this.unitusaha = unitusaha;
    }

    public Double getInvestasi() {
        return investasi;
    }

    public void setInvestasi(Double investasi) {
        this.investasi = investasi;
    }

    public String getTenaga_kerja() {
        return tenaga_kerja;
    }

    public void setTenaga_kerja(String tenaga_kerja) {
        this.tenaga_kerja = tenaga_kerja;
    }

    public String getNama_sektor() {
        return nama_sektor;
    }

    public void setNama_sektor(String nama_sektor) {
        this.nama_sektor = nama_sektor;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
