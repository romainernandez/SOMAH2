package eps.somah2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPeriodsActivity extends AppCompatActivity {

    private ListView listView;
    private PeriodAdapter periodAdapter;
    private List<Period> periodList;
    //private String[] periodNames;
    private ArrayList<String> periodNames;
    private ArrayList<String> periodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_periods);

        periodList = new LinkedList<Period>();

        Database db = new Database(this);
        ArrayList<ArrayList<String>> periodTable = db.readTableTEST(db.table_period);
        periodNames = periodTable.get(1);
        periodID = periodTable.get(0);

        //periodNames = getResources().getStringArray(R.array.periods);
        //for (int i=0; i<periodNames.length; i++){
        for (int i=0; i<periodNames.size();i++){
            Period period = new Period();
            //period.setId(i+1);
            period.setId(Integer.parseInt(periodID.get(i)));

            Log.i("ID-period" + i + " : ", periodTable.get(0).get(i) );
            //period.setName(periodNames[i]);
            period.setName(periodNames.get(i));
            period.setImage(getResources().getIdentifier("period"+(i+1), "drawable", getPackageName()));
            periodList.add(period);
        }

        listView = (ListView) findViewById(R.id.listView);
        periodAdapter = new PeriodAdapter(this, R.layout.period, periodList);
        listView.setAdapter(periodAdapter);
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
            case (R.id.action_settings):
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
