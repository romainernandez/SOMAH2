package eps.somah2;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import java.util.Collections;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private Locale locale;
    private boolean isDatabaseCreated = false;
    private Database db;
    private String languageName; // current language name
    private String languageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        FrameLayout welcomeScreenLayout = (FrameLayout) findViewById(R.id.welcomeLayout);
        Spinner dropdown = (Spinner)findViewById(R.id.welcomeSpinner);

        locale = getResources().getConfiguration().locale;
        languageCode = locale.getLanguage();
        languageName = locale.getDisplayLanguage();
        Log.d("Romain", "onCreate: languageCode=" + languageCode);
        Log.d("Romain", "onCreate: languageName=" + languageName);

        if (!isDatabaseCreated)
            db = new Database(this);
            //isDatabaseCreated = true; TODO: store in SharePreferances
        // TODO: else
        // db =

        ArrayList<ArrayList<String>> languageTable = db.readTableTEST(db.table_language);
        ArrayList<String> allLanguagesNames = languageTable.get(1);
        Collections.sort(allLanguagesNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, allLanguagesNames);
        dropdown.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(languageName);
        Log.d("Romain", "adapter.getPosition(languageName)= "  + Integer.toString(spinnerPosition));
        dropdown.setSelection(spinnerPosition);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Romain", "onItemSelected: position= " + Integer.toString(position));
                String newLanguageName = parent.getItemAtPosition(position).toString();
                Log.d("Romain", "newLanguageName= " + newLanguageName);

                if (! newLanguageName.equals(languageName)) {
                    Log.d("Romain", "difference");
                    String newLanguageCode = "fr";
                    // TODO: request languagecode from db
                    Log.d("Romain", "newLanguageCode= " + newLanguageCode);
                    setLocale(newLanguageCode);
                }
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
        languageName = lang;
        Intent refresh = getIntent();
        finish();
        startActivity(refresh);
    }
}
