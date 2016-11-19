package eps.somah2;

import android.util.Log;

import java.sql.Blob;

/**
 * Created by rernande on 09/11/2016.
 */

public class NamedPeriod {
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
