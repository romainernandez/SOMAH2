package eps.somah2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Oktay on 11/8/2016.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SOMAH2.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private String actualLanguage = "E";
    //public String test = ;


    //DATA PERIOD
    public static final String table_period = "Period";
    private static final String ID = "ID";
    private static final String periodID = "Period_ID";
    private static final String name = "Name";
    private static final String create_period = ""
            + "CREATE TABLE "+table_period+" ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + name + " TEXT );";

    // DATA TOPIC
    public static final String table_topic = "Topic";
    private static final String topicID = "Topic_ID";
    private static final String create_topic = ""
            + "CREATE TABLE " + table_topic + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + name + " TEXT); ";



    // DATA PERIOD_TOPIC
    public static final String table_period_topic = "Period_Topic";
    private static final String create_period_topic = ""
            + "CREATE TABLE " + table_period_topic + " ("
            + periodID + " INTEGER, "
            + topicID + " INTEGER, "
            + " PRIMARY KEY(" + periodID + "," + topicID +"), "
            + " FOREIGN KEY ( " + periodID + " ) REFERENCES " + table_period + " ( " + ID + " ), "
            + " FOREIGN KEY ( " + topicID + " ) REFERENCES " + table_topic + " ( " + ID + " )); ";


    // DATA TEXT
    private static final String table_text = "Text";
    private static final String textID = "Text_ID";
    private static final String description = "Description";
    private static final String title = "Title";
    private static final String create_text = ""
            + "CREATE TABLE " + table_text + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + title + " TEXT, "
            + description + " TEXT );";

    // DATA PICTURE
    private static final String table_picture = "Picture";
    private static final String pictureID = "Picture_ID";
    private static final String create_picture =  ""
            + "CREATE TABLE " + table_picture + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + title + " TEXT, "
            + description + " TEXT );";

    // DATA Language
    public static final String table_language = "Language";
    private static final String id = "ID";
    private static final String language = "Language";
    private static final String languageID = "Language_ID";
    private static final String create_language = ""
            + "CREATE TABLE " + table_language + " ("
            + id + " TEXT PRIMARY KEY, "
            + language + " TEXT );";

    //DATA PERIODTR
    private static final String table_periodTR = "Period_TR";
    private static final String create_periodTR = ""
            + "CREATE TABLE " + table_periodTR + " ("
            + periodID + " INTEGER NOT NULL, "
            + languageID + " TEXT NOT NULL, "
            + name + " TEXT, "
            + " PRIMARY KEY(" + periodID + "," + languageID +"), "
            + " FOREIGN KEY ( " + periodID + " ) REFERENCES " + table_period + " ( " + ID + " ), "
            + " FOREIGN KEY ( " + languageID + " ) REFERENCES " + table_language + " ( " + id + " )); ";

    //DATA TOPICTR
    private static final String table_topicTR = "Topic_TR";
    private static final String create_topicTR = ""
            + "CREATE TABLE " + table_topicTR + " ("
            + topicID + " INTEGER NOT NULL, "
            + languageID + " TEXT NOT NULL, "
            + name + " TEXT, "
            + " PRIMARY KEY(" + topicID + "," + languageID +"), "
            + " FOREIGN KEY ( " + topicID + " ) REFERENCES " + table_topic + " ( " + ID + " ), "
            + " FOREIGN KEY ( " + languageID + " ) REFERENCES " + table_language + " ( " + id + " )); ";

    //DATA TEXTTR
    private static final String table_textTR = "Text_TR";
    private static final String create_textTR = ""
            + "CREATE TABLE " + table_textTR + " ("
            + textID + " INTEGER NOT NULL, "
            + languageID + " TEXT NOT NULL, "
            + title + " TEXT, "
            + description + " TEXT, "
            + " PRIMARY KEY(" + textID + "," + languageID +"), "
            + " FOREIGN KEY ( " + textID + " ) REFERENCES " + table_text + " ( " + ID + " ), "
            + " FOREIGN KEY ( " + languageID + " ) REFERENCES " + table_language + " ( " + id + " )); ";

    // CONTENT
    private static final String table_content = "Content_Page";
    private static final String create_content = ""
            + "CREATE TABLE " + table_content + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + topicID + " INTEGER, "
            + textID + " INTEGER NOT NULL, "
            + pictureID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ( " + textID + " ) REFERENCES " + table_text + " ( " + ID + " ), "
            + " FOREIGN KEY ( " + topicID + " ) REFERENCES " + table_topic + " ( " + ID + " )); ";



    // CONSTRUCTOR
    public Database (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
        doAllTables(db);

        //String test = new String();
        //test = WelcomeActivity.getCurrentLang();
        //Log.d("Romain", );

        /*
        //ArrayList<ArrayList<String>> listTable = readTableTEST(table_language);
        ArrayList<ArrayList<String>> listTable = readTableTEST(table_topic);

        Log.i("size", "" + listTable.size());
        for (int i = 0; i < listTable.size() ; i++)
        {
            Log.i("listTable","" + listTable.get(i));

        }
        */

        //readContentTable(1);
        //showArray(readTableTEST(table_content,1));
    }

    private void doAllTables(SQLiteDatabase db){
        doPeriod(db, table_period, create_period);
        Log.i("-----------","----------");
        doTopic(db, table_topic, create_topic);
        Log.i("-----------","----------");
        doText(db, table_text, create_text);
        Log.i("-----------","----------");
        doPeriodTR(db, table_periodTR, create_periodTR);
        Log.i("-----------","----------");
        doTopicTR(db, table_topicTR, create_topicTR);
        Log.i("-----------","----------");
        doTextTR(db, table_textTR, create_textTR);
        Log.i("-----------","----------");
        doLanguage(db, table_language, create_language);
        Log.i("-----------","----------");
        doContent(db, table_content, create_content);
    }
    // TABLE_PERIOD
    private void doPeriod(SQLiteDatabase db, String tableName, String createName){
        Log.i("create",tableName + " table");
        createTable(db, tableName, createName);
        Log.i("insert",tableName + " data");


        insertTable(tableName, " pregnancy new");
        insertTable(tableName, " 0 - 2 weeks");
        insertTable(tableName, " 2 - 4 weeks");
        insertTable(tableName, " 6 weeks");
        insertTable(tableName, " 3 months");
        insertTable(tableName, " 4 months");
        insertTable(tableName, " 5 months");
        insertTable(tableName, " 6 months");
        insertTable(tableName, " 7-8 months");
        insertTable(tableName, " 10 months");
        insertTable(tableName, " 11-12 months");
        insertTable(tableName, " 15 months");
        insertTable(tableName, " 17-18 months");
        insertTable(tableName, " 2 years");


        Log.i("read",tableName + " data");
        readTable(tableName);
    }

    // TABLE TOPIC
    private void doTopic(SQLiteDatabase db, String tableName, String createTable){
        Log.i("Create",tableName + " table");
        createTable(db, table_topic, create_topic);
        Log.i("insert",tableName + " data");
        insertTable(tableName, " Value of breastfeeding. Why?");
        insertTable(tableName, " How breastfeeding");
        insertTable(tableName, " Problems with reastfeeding");
        insertTable(tableName, " How often?");

        /*
        insertTable(tableName, " Reaction?");
        insertTable(tableName, " Sleep?");
        insertTable(tableName, " Holding");
        */

        Log.i("read","topic data");
        readTable(tableName);
    }

    // TABLE PERIOD_TOPIC
    private void doPeriodTopic(SQLiteDatabase db, String tableName, String createTable){
        Log.i("Create",tableName + " table");
        createTable(db, table_period_topic, create_period_topic);

        insertTable(tableName, 1, 1);
        insertTable(tableName, 1, 2);
        insertTable(tableName, 1, 3);
        insertTable(tableName, 1, 4);
        insertTable(tableName, 2, 2);
        insertTable(tableName, 2, 5);
        insertTable(tableName, 2, 6);
        insertTable(tableName, 2, 7);

        Log.i("read","topic data");
        readTable(tableName);
    }


    // TABLE TEXT
    private void doText(SQLiteDatabase db, String tableName, String createTable){
        Log.i("Create",tableName + " table");
        createTable(db, tableName, createTable);
        Log.i("insert",tableName + " data");
        insertTable(tableName, "Step 1", "Holding baby like this");
        Log.i("read",tableName +" data");
        readTable(tableName);
    }

    // TABLE periodTR
    private void doPeriodTR(SQLiteDatabase db, String tableName, String createTable){
        Log.i("Create",tableName + " table");
        createTable(db, tableName, createTable);
        Log.i("insert", tableName +" data");
        insertTranslateTable(tableName, 1, "DE", "Die Schwangerschaft");
        insertTranslateTable(tableName, 1, "EN", " Pregnancy");
        insertTranslateTable(tableName, 2, "DE", " 0 - 2 wochen");
        insertTranslateTable(tableName, 2, "EN", " 0 - 2 weeks");
        insertTranslateTable(tableName, 3, "DE", " 2 - 4 wochen");
        insertTranslateTable(tableName, 3, "EN", " 2 - 4 weeks");
        insertTranslateTable(tableName, 4, "EN", " 6 weeks");
        insertTranslateTable(tableName, 4, "DE", " 6 wochen");
        insertTranslateTable(tableName, 5, "EN", " 3 months");
        insertTranslateTable(tableName, 5, "DE", " 3 monate");
        insertTranslateTable(tableName, 6, "EN", " 4 months");
        insertTranslateTable(tableName, 6, "DE", " 4 monate");
        insertTranslateTable(tableName, 7, "EN", " 5 months");
        insertTranslateTable(tableName, 7, "DE", " 5 monate");
        insertTranslateTable(tableName, 8, "EN", " 6 months");
        insertTranslateTable(tableName, 8, "DE", " 6 monate");
        insertTranslateTable(tableName, 9, "EN", " 7 - 8 months");
        insertTranslateTable(tableName, 9, "DE", " 7 - 8 monate");
        insertTranslateTable(tableName, 10, "EN", " 10 months");
        insertTranslateTable(tableName, 10, "DE", " 10 monate");
        insertTranslateTable(tableName, 11, "EN", " 11-12 months");
        insertTranslateTable(tableName, 11, "DE", " 11-12 monate");
        insertTranslateTable(tableName, 12, "EN", " 15 months");
        insertTranslateTable(tableName, 12, "DE", " 15 monate");
        insertTranslateTable(tableName, 13, "EN", " 17-18 months");
        insertTranslateTable(tableName, 13, "DE", " 17-18 monate");
        insertTranslateTable(tableName, 14, "EN", " 2 years");
        insertTranslateTable(tableName, 14, "DE", " 2 jahre");
        Log.i("read", tableName + " data");
        readTable(tableName);
    }

    //TABLE topicTR
    private void doTopicTR(SQLiteDatabase db, String tableName, String createTable){
        Log.i("Create",tableName + " table");
        createTable(db, tableName, createTable);
        Log.i("insert", tableName +" data");
        insertTranslateTable(tableName, 1,"E", "Value of breastfeeding. Why?");
        insertTranslateTable(tableName, 2,"E", "How breastfeeding");
        insertTranslateTable(tableName, 3,"E", "Problems with reastfeeding");
        insertTranslateTable(tableName, 4,"E", "How often?");
        insertTranslateTable(tableName, 1, "DE","Eigenschaften des Stillen");
        insertTranslateTable(tableName, 2, "DE","Wie stillen?");
        insertTranslateTable(tableName, 3, "DE","Probleme mit dem Stillen");
        insertTranslateTable(tableName, 4, "DE","Wie oft Stillen?");

        Log.i("read", tableName + " data");
        readTable(tableName);
    }

    //TABLE textTR
    private void doTextTR(SQLiteDatabase db, String tableName, String createTable){
        Log.i("Create",tableName + " table");
        createTable(db, tableName, createTable);
        Log.i("insert", tableName +" data");
        insertTranslateTable(tableName, 1, "DE","Schritt 1", "Baby so halten");
        insertTranslateTable(tableName, 1, "E","Step 1", "Hold baby like this");
        insertTranslateTable(tableName, 2, "DE","Schritt 2", "So stillen");
        insertTranslateTable(tableName, 2, "E","Step 2", "Breastfeeding like this");

        Log.i("read", tableName + " data");
        readTable(tableName);
    }

    // TABLE_Language
    private void doLanguage(SQLiteDatabase db, String tableName, String createTable){
        Log.i("create", table_language + " table");
        createTable(db, tableName, createTable);
        Log.i("insert",table_language + " data");
        insertTable(tableName, "DE", "German");
        insertTable(tableName, "EN", "English");
        insertTable(tableName, "FR", "French");
        insertTable(tableName, "NO", "Norwegian");
        insertTable(tableName, "SP", "Spanish");
        Log.i("read",table_language + " data");
        readTable(tableName);
    }

    // TABLE CONTENT
    private void doContent(SQLiteDatabase db, String tableName, String createTable){
        Log.i("create", tableName + " table");
        createTable(db, tableName, createTable);
        Log.i("insert",tableName + " data");
        insertContentTable(tableName, 1,1,1 );
        insertContentTable(tableName, 1,2,1 );
        Log.i("read",tableName + " data");
        readTable(tableName);
    }

    // CREATE INSERT READ
    public void createTable(SQLiteDatabase db, String tableName, String createTable){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            db.execSQL(createTable);
            Log.i("db created:", tableName);
        } catch(Exception e){
            Log.i("db creating",tableName +" fail");
        }
    }


    public void insertTable (String tableName, String name2){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_period) || tableName.equals(table_topic)) {
            values.put(name, name2);
        }
        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }

    public void insertTable (String tableName, int id, int id2){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_period_topic)) {
            values.put(periodID, id);
            values.put(topicID, id2);
        }
        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }

    public void insertTable (String tableName, int id, String name){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_topic)) {
            values.put(periodID, id);
            values.put(this.name, name);
        }
        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }
    public void insertTable (String tableName, String title, String description){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_text)) {
            values.put(this.title, title);
            values.put(this.description, description);
        }else if(tableName.equals(table_language)) {
            values.put(id, title);
            values.put(language, description);
        }

        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }
    public void insertTranslateTable( String tableName, int contentID, String langID, String title){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_periodTR)) {
            values.put(periodID, contentID);
            values.put(languageID, langID);
            values.put(name, title);
        } else if (tableName.equals(table_topicTR)) {
            values.put(topicID, contentID);
            values.put(languageID, langID);
            values.put(name, title);
        }
        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }
    public void insertTranslateTable( String tableName, int contentID, String langID, String title, String description){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_textTR)) {
            values.put(textID, contentID);
            values.put(languageID, langID);
            values.put(this.title, title);
            values.put(this.description, description);
        }
        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }
    public void insertContentTable (String tableName, int topicID2, int textID2, int pictureID2){
        long insertID;
        ContentValues values = new ContentValues();
        if(tableName.equals(table_content)) {
            values.put(topicID, topicID2);
            values.put(textID, textID2);
            values.put(pictureID, pictureID2);
        }
        insertID = db.insert(tableName, null , values);
        Log.i("Data"," in "+tableName+" inserted");
    }
    public void readTable (String tableName){
        String [] columns = null;
        if(tableName.equals(table_periodTR))
            columns = new String[]{periodID, languageID, name};
        else if(tableName.equals(table_period_topic))
            columns = new String[]{periodID, topicID};
        else if (tableName.equals(table_topicTR))
            columns = new String [] {topicID, languageID, name};
        else if (tableName.equals(table_textTR))
            columns = new String [] {textID, languageID, title, description};
        else if (tableName.equals(table_text))
            columns = new String [] {ID, title, description};
        else if (tableName.equals(table_topic))
            columns = new String [] {ID, name};
        else if (tableName.equals(table_period))
            columns = new String [] {ID, name};
        else if (tableName.equals(table_language))
            columns = new String [] {id, language};
        else if (tableName.equals(table_content))
            columns = new String [] {ID, topicID, textID, pictureID};

        Cursor cursor = db.query(tableName,columns,null,null,null,null,null,null);
        if (cursor != null) {
            Log.i("Data","in the "+tableName);
            Log.i("Data set",String.valueOf(cursor.getCount()));
            cursor.moveToFirst();
            boolean next = true;
            //for(int d=0; d<cursor.getCount();d++)
            while (next) {
                for (int i = 0; i < columns.length; i++) {
                    String c = cursor.getString(i);
                    Log.i("sepcial ", cursor.getColumnName(i) + " " + c);
                }
                next = cursor.moveToNext();
            }
        } else {
            Log.i("Data","not in the table");
        }
        Log.i("Table "+tableName," read");
    }

    public ArrayList<String> readTable (int key, String tablename) {
        ArrayList<String> list = new ArrayList<String>();

        String[] columns = null;
        if (tablename.equals(table_period_topic)) {
            columns = new String[]{topicID};
            String selection = periodID + "= ?";
            String[] where = new String[]{String.valueOf(key)};
            Cursor cursor = db.query(tablename, columns, selection, where, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                boolean next = true;
                while (next) {
                    for (int i = 0; i < columns.length; i++) {
                        String c = cursor.getString(i);
                        list.add(c);
                    }
                    next = cursor.moveToNext();
                }
            }
        }
        return list;
    }

    public ArrayList<ArrayList<String>> readTableTEST (String tableName){
        String [] columns = null;
        if(tableName.equals(table_periodTR))
            columns = new String[]{periodID, name};
        else if (tableName.equals(table_topicTR))
            columns = new String [] {topicID, name};
        else if (tableName.equals(table_textTR))
            columns = new String [] {textID, title, description};
        else if (tableName.equals(table_text))
            columns = new String [] {ID, title, description};
        else if (tableName.equals(table_topic))
            columns = new String [] {ID, name};
        else if (tableName.equals(table_period))
            columns = new String [] {ID, name};
        else if (tableName.equals(table_language))
            columns = new String [] {id, language};
        else if (tableName.equals(table_content))
            columns = new String [] {ID, topicID, textID, pictureID};

        String [][] list;
        ArrayList<String> idColumn = new ArrayList<String>();
        ArrayList<String> nameColumn = new ArrayList<String>();
        ArrayList<ArrayList<String>> listTable = new ArrayList<ArrayList<String>>();

        Cursor cursor = db.query(tableName,columns,null,null,null,null,null,null);

        if (cursor != null) {
            Log.i("Data","in the "+tableName);
            Log.i("Data set",String.valueOf(cursor.getCount()));
            list = new String [cursor.getCount()][2];
            cursor.moveToFirst();
            boolean next = true;
            //for(int d=0; d<cursor.getCount();d++)
            while (next) {

                for (int i = 0; i < columns.length; i++) {
                    String c = cursor.getString(i);
                    if(i%2==0)
                        idColumn.add(c);
                    else
                        nameColumn.add(c);
                    Log.i("sepcial ", cursor.getColumnName(i) + " " + cursor.getString(i));
                }
                next = cursor.moveToNext();
            }

        } else {
            Log.i("Data","not in the table");
        }
        Log.i("Table "+tableName," read");
        listTable.add(idColumn);
        listTable.add(nameColumn);



        return listTable;
    }

    private void showArray (String [][] list){
        for ( int i = 0; i< list.length; i++)
            for (int d = 0; d< list [i].length; d++)
                Log.w("Array" , list [i][d]);
    }

    public String readTRTableString (String tableName,String column, int key, int counter){
        String [] columns = new String[]{column};
        String  selection = ID + " = ? ";
        if (tableName.equals(table_periodTR)||tableName.equals(table_textTR)||tableName.equals(table_topicTR))
            selection = ID + " = ? AND " + languageID + " = " + actualLanguage ;
        String [] where = new String [] {String.valueOf(key)};

        Cursor cursor = db.query(tableName,columns,selection,where,null,null,null,null);
        if (cursor != null) {
            cursor.moveToPosition(counter);
            return cursor.getString(0);
        } else {
            Log.i("Data","not in the table");
        }

        return null;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
