package eps.somah2;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * Created by rernande on 16/11/2016.
 */

public class MyApplication extends Application{
    protected static MyApplication instance;
    private Locale locale = null;
    private String languageName;
    private String languageCode;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        locale = getResources().getConfiguration().locale;
        this.setLanguageName(locale.getDisplayLanguage());
        this.setLanguageCode(locale.getLanguage());
        Log.d("Romain", "MyApplication: onCreate: lang= " + locale.getDisplayLanguage());
    }

    public void setLocale(String lang) {
        locale = new Locale(lang);
        Resources res = getResources();
        // Change locale settings in the app
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        this.setLanguageName(locale.getDisplayLanguage());
        this.setLanguageCode(locale.getLanguage());
        Log.d("Romain", "setLocale: getLanguageName= " + getLanguageName());
        res.updateConfiguration(conf, dm);
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

}
