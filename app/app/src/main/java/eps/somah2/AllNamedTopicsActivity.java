package eps.somah2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

public class AllNamedTopicsActivity extends AppCompatActivity {

    private NamedTopicAdapter namedTopicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_named_topics);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        int periodId = getIntent().getIntExtra("period_id", 0);
        Log.d("Romain", "AllNamedTopicsActivity: onCreate: getIntExtra= " + periodId);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        List<NamedTopic> namedTopicList = databaseHelper.getAllNamedTopics(periodId);
        namedTopicAdapter = new NamedTopicAdapter(this, R.layout.topic, namedTopicList);
        gridView.setAdapter(namedTopicAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), AllTextedContentActivity.class);
                intent.putExtra("topic_id", namedTopicAdapter.getItem(position).getId());
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
