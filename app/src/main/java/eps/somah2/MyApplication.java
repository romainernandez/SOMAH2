package eps.somah2;

import android.app.Application;

/**
 * Created by rernande on 16/11/2016.
 */

public class MyApplication extends Application{
    protected static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    private String languageName;
    private String languageCode;

}
