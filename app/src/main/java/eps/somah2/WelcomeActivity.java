package eps.somah2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        FrameLayout welcomeScreenLayout = (FrameLayout) findViewById(R.id.welcomeLayout);
        Spinner dropdown = (Spinner)findViewById(R.id.welcomeSpinner);

        final DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        // spinner
        ArrayList<String> allLanguagesNames = databaseHelper.readTable(databaseHelper.LANGUAGE_NAME, databaseHelper.TABLE_LANGUAGE);
        Collections.sort(allLanguagesNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, allLanguagesNames);
        dropdown.setAdapter(adapter);
        // set app language as default spinner value
        final MyApplication app = (MyApplication) getApplicationContext();
        int spinnerPosition = adapter.getPosition(app.getLanguageName());
        dropdown.setSelection(spinnerPosition, false);
        Log.d("Romain", "setSelection: app.getLanguageName()= " + app.getLanguageName());

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newLanguageName = parent.getItemAtPosition(position).toString();

                Log.d("Romain", "onItemSelected: newLanguageName= " + newLanguageName);
                if (! newLanguageName.equals(app.getLanguageName())) {
                    String newLanguageCode = databaseHelper.readTable(databaseHelper.LANGUAGE_CODE, databaseHelper.TABLE_LANGUAGE, databaseHelper.LANGUAGE_NAME, newLanguageName).get(0);
                    app.setLocale(newLanguageCode);
                    Intent refresh = getIntent();
                    finish();
                    startActivity(refresh);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        welcomeScreenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, AllNamedPeriodsActivity.class));
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
            case R.id.settings:
                startActivity(new Intent(WelcomeActivity.this, SettingsActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}