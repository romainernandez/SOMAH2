package eps.somah2;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
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
    private String serverUrl;

    @Override
    public void onCreate() {
        super.onCreate();

        this.locale = getResources().getConfiguration().locale;
        String languageName = locale.getDisplayLanguage(locale);
        languageName = languageName.substring(0, 1).toUpperCase() + languageName.substring(1);
        this.setLanguageName(languageName);
        this.setLanguageCode(locale.getLanguage());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // 10.0.2.2 points to local web server
        this.serverUrl = preferences.getString("pref_key_server_url", "http://10.0.2.2/");

        instance = this;
    }

    public void setLocale(String lang) {
        locale = new Locale(lang);
        Resources res = getResources();
        // Change locale settings in the app
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        String languageName = locale.getDisplayLanguage(locale);
        languageName = languageName.substring(0, 1).toUpperCase() + languageName.substring(1);
        this.setLanguageName(languageName);
        Log.d("Romain", "setLocale: languageName= " + this.getLanguageName());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("Romain", "setLocale: pref_key_server_url= " + preferences.getString("pref_key_server_url", "http://10.0.2.2/"));
        this.setLanguageCode(locale.getLanguage());
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

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("pref_key_server_url", "http://10.0.2.2/");
    }

}
