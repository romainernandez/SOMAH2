package eps.somah2;

import android.util.Log;

/**
 * Created by rernande on 09/11/2016.
 */

public class Period {
    private int id;
    private String name;
    private int imageId;

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

    public int getImage() {
        Log.i("Show imageID", "" + id);
        return imageId;
    }
    public void setImage(int imageId) { this.imageId = imageId; }
}
