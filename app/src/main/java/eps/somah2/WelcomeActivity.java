package eps.somah2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.R.attr.path;

public class WelcomeActivity extends AppCompatActivity {

    public static final String PREFERENCES = "Prefs" ;
    public static final String DB_PREF = "IS_DB_CREATED";

    SharedPreferences sharedpreferences;

    private Locale locale;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        FrameLayout welcomeScreenLayout = (FrameLayout) findViewById(R.id.welcomeLayout);
        Spinner dropdown = (Spinner)findViewById(R.id.welcomeSpinner);

        sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        locale = getResources().getConfiguration().locale;
        MyApplication app = (MyApplication) getApplicationContext();
        app.setLanguageName(locale.getDisplayLanguage());
        app.setLanguageCode(locale.getLanguage());

        Boolean isDatabaseCreated = (sharedpreferences.getBoolean(DB_PREF, false));

        if (!isDatabaseCreated || true) {
            db = new Database(this);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(DB_PREF, true);
            editor.commit();

        }
        else {

            ;// TODO: Database.openDataBase();
        }

        app.setDb(db);
        ArrayList<String> allLanguagesNames = db.readTable(db.LANGUAGE_NAME,db.TABLE_LANGUAGE);
        Collections.sort(allLanguagesNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, allLanguagesNames);
        dropdown.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(app.getLanguageName());
        Log.d("Romain", "adapter.getPosition(languageName)= "  + Integer.toString(spinnerPosition));
        dropdown.setSelection(spinnerPosition);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Romain", "onItemSelected: position= " + Integer.toString(position));
                String newLanguageName = parent.getItemAtPosition(position).toString();
                Log.d("Romain", "newLanguageName= " + newLanguageName);

                /*
                if (! newLanguageName.equals(MyApplication.instance.getLanguageName())) {
                    Log.d("Romain", "difference");
                    String newLanguageCode = "en";
                    // TODO: request languagecode from db
                    Log.d("Romain", "newLanguageCode= " + newLanguageCode);
                    setLocale(newLanguageCode);
                }
                */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ;
            }
        });

        welcomeScreenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, AllPeriodsActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(WelcomeActivity.this, SettingsActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setLocale(String lang) {
        /**
         * Change language configuration and refresh MainActivity
         * @params lang abbreviation of language to be set ("en"/"fr")
         */
        locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        MyApplication.instance.setLanguageName(lang);
        MyApplication.instance.setLanguageCode("fr");
        // TODO: app.setLanguageCode();
        Intent refresh = getIntent();
        finish();
        startActivity(refresh);
    }
}