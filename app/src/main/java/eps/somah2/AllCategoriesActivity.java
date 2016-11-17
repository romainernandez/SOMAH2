package eps.somah2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity {

    private GridView gridView;
    private CategoryAdapter categoryAdapter;

    private List<Category> categoryList;
    private ArrayList<String> topicNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        String PeriodID = null;
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            PeriodID = extras.getString("ID");
            Log.i("PeriodIDSelected : ",PeriodID);
        }

        MyApplication app = (MyApplication) getApplicationContext();
        Database db = app.getDb();

        topicNames = db.readTable(db.TOPIC_TR_NAME,db.TABLE_TOPIC_TR);
        categoryList = new LinkedList<Category>();

        for (int i=0; i<topicNames.size(); i++){
            Category category = new Category();
            category.setId(i+1);
            category.setName(topicNames.get(i));
            category.setImage(getResources().getIdentifier("image"+(i+1), "drawable", getPackageName()));
            categoryList.add(category);
        }

        gridView = (GridView) findViewById(R.id.gridView);
        categoryAdapter = new CategoryAdapter(this, R.layout.category, categoryList);
        gridView.setAdapter(categoryAdapter);

        //TODO: registerForContextMenu(gridView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
            case (R.id.action_settings):
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
