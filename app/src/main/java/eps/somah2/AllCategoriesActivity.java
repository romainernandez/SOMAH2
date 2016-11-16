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
    //private String[] categoryNames;
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

        Database db = new Database(this);


        ArrayList <String> topicIDSelected = db.readTable(Integer.parseInt(PeriodID),db.table_period_topic);
        for (int i = 0; i < topicIDSelected.size() ; i++)
        {
            Log.i("listTableSelected","" + topicIDSelected.get(i));
        }


        //ArrayList<ArrayList<String>> periodTopicTable = db.readTableTEST(db.table_period_topic);

        /*
        ArrayList <String> topicNameSelected = null;
        for (int i = 0; i < topicIDSelected.size() ; i++)
        {
            topicNameSelected = db.readTable2(Integer.parseInt(topicIDSelected.get(i)),db.table_topic);

        }
        for (int i = 0; i < topicNameSelected.size() ; i++)
            Log.i("TopicNameSelected","" + topicNameSelected.get(i));
        */


        ArrayList<ArrayList<String>> topicTable = db.readTableTESTTopic(db.table_topic, topicIDSelected);

        topicNames = topicTable.get(1);

        categoryList = new LinkedList<Category>();

        //categoryNames = getResources().getStringArray(R.array.categories);
        //for (int i=0; i<categoryNames.length; i++){
        for (int i=0; i<topicNames.size(); i++){
            Category category = new Category();
            category.setId(i+1);
            //category.setName(categoryNames[i]);
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
