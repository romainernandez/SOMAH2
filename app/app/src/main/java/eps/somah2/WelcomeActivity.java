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

public class WelcomeActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        FrameLayout welcomeScreenLayout = (FrameLayout) findViewById(R.id.welcomeLayout);
        Spinner dropdown = (Spinner)findViewById(R.id.welcomeSpinner);

        databaseHelper = DatabaseHelper.getInstance(this);
        app = (MyApplication) getApplicationContext();

        // spinner include all available languages of the app
        ArrayList<String> languagesNames = databaseHelper.getAllLanguagesNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, languagesNames);
        dropdown.setAdapter(adapter);

        // set app language as the default spinner's value
        int spinnerPosition = adapter.getPosition(app.getLanguageName());
        dropdown.setSelection(spinnerPosition, false);
        Log.d("Romain", "onCreate: dropdown.setSelection: app.getLanguageName()= " + app.getLanguageName());

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newLanguageName = parent.getItemAtPosition(position).toString();
                Log.d("Romain", "onItemSelected: newLanguageName= " + newLanguageName);
                if (! newLanguageName.equals(app.getLanguageName())) {
                    // change language
                    String newLanguageCode = databaseHelper.getLanguageCode(newLanguageName);
                    app.setLocale(newLanguageCode);
                    finish();
                    startActivity(getIntent());
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