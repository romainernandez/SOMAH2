package eps.somah2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllNamedPeriodsActivity extends AppCompatActivity {

    private ListView listView;
    private NamedPeriodAdapter namedPeriodAdapter;
    private ArrayList<NamedPeriod> namedPeriodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_named_periods);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        namedPeriodList = databaseHelper.getAllNamedPeriods();

        listView = (ListView) findViewById(R.id.listView);
        namedPeriodAdapter = new NamedPeriodAdapter(this, R.layout.period, namedPeriodList);
        listView.setAdapter(namedPeriodAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), AllNamedTopicsActivity.class);
                intent.putExtra("period_id", namedPeriodAdapter.getItem(position).getId());
                startActivity(intent);
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
            case (R.id.settings):
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}