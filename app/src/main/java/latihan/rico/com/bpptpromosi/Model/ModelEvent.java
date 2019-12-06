package latihan.rico.com.bpptpromosi.Model;

public class ModelEvent {

    public int id;
    public String judul_event;
    public String deskripsi;
    public String video;


    public ModelEvent(int id, String judul_event, String deskripsi, String video) {
        this.id = id;
        this.judul_event = judul_event;
        this.deskripsi = deskripsi;
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul_event() {
        return judul_event;
    }

    public void setJudul_event(String judul_event) {
        this.judul_event = judul_event;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }



}
