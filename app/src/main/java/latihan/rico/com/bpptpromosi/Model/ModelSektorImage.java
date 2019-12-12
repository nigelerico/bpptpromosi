package latihan.rico.com.bpptpromosi.Model;


public class ModelSektorImage {
    public int id;
    public String foto;

    public ModelSektorImage(int id, String foto) {
        this.id = id;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
