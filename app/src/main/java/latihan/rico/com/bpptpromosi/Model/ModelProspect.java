package latihan.rico.com.bpptpromosi.Model;

public class ModelProspect {

    private int id;
    private String nama;
    private String tempat;
    private String bidang;
    private int gambar;



    public ModelProspect(int id, String nama, String tempat, String bidang, int gambar) {
        this.id = id;
        this.nama = nama;
        this.tempat = tempat;
        this.bidang = bidang;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
