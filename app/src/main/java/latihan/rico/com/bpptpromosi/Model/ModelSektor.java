package latihan.rico.com.bpptpromosi.Model;

public class ModelSektor {
    private int id;
    private String value;
    private String logo;

    public ModelSektor(int id, String value, String logo) {
        this.id = id;
        this.value = value;
        this.logo = logo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
