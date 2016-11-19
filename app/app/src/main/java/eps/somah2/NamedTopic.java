package eps.somah2;

/**
 * Created by rernande on 21/10/2016.
 */

public class NamedTopic {
    private int id;
    private String name;

    private byte[] image;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
