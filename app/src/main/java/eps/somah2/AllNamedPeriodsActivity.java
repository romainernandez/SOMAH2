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
import java.util.LinkedList;
import java.util.List;

public class AllNamedPeriodsActivity extends AppCompatActivity {

    private ListView listView;
    private NamedPeriodAdapter namedPeriodAdapter;
    private List<NamedPeriod> namedPeriodList;
    private ArrayList<String> periodNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_periods);

        namedPeriodList = new LinkedList<NamedPeriod>();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        periodNames = databaseHelper.readTable(databaseHelper.PERIOD_TR_NAME,databaseHelper.TABLE_PERIOD_TR);
        // TODO: PRIO
        for (int i=0; i<periodNames.size();i++){
            NamedPeriod namedPeriod = new NamedPeriod();
            namedPeriod.setId(i+1);
            namedPeriod.setName(periodNames.get(i));
            namedPeriod.setImage(getResources().getIdentifier("namedPeriod"+(i+1), "drawable", getPackageName()));
            namedPeriodList.add(namedPeriod);
        }

        listView = (ListView) findViewById(R.id.listView);
        namedPeriodAdapter = new NamedPeriodAdapter(this, R.layout.period, namedPeriodList);
        listView.setAdapter(namedPeriodAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), AllCategoriesActivity.class);
                // position = period_id
                intent.putExtra("period_id", position);
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