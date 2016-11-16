package eps.somah2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oktay on 11/8/2016.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SOMAH2.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private boolean insertMockData = true;
    private String actualLanguage = MyApplication.instance.getLanguageCode();

    // TABLE_PERIOD
    public static final String TABLE_PERIOD = "period";
    private static final String PERIOD_ID = "id";
    private static final String PERIOD_IMAGE = "image";

    private static final String CREATE_TABLE_PERIOD = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_PERIOD+" ("
            + PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PERIOD_IMAGE + " BLOB NOT NULL);";

    // TABLE_TOPIC
    public static final String TABLE_TOPIC = "topic";
    private static final String TOPIC_ID = "id";
    private static final String TOPIC_IMAGE = "image";
    private static final String CREATE_TABLE_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_TOPIC + " ("
            + TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TOPIC_IMAGE + " BLOB NOT NULL); ";

    // TABLE_ASSOCIATION_PERIOD_TOPIC
    public static final String TABLE_ASSOCIATION_PERIOD_TOPIC = "association_period_topic";
    private static final String ASSOCIATION_PERIOD_TOPIC_PERIOD_ID = "period_id";
    private static final String ASSOCIATION_PERIOD_TOPIC_TOPIC_ID = "topic_id";
    private static final String CREATE_TABLE_ASSOCIATION_PERIOD_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_ASSOCIATION_PERIOD_TOPIC + " ("
            + ASSOCIATION_PERIOD_TOPIC_PERIOD_ID + " INTEGER NOT NULL, "
            + ASSOCIATION_PERIOD_TOPIC_TOPIC_ID + " INTEGER NOT NULL, "
            + " PRIMARY KEY(" + ASSOCIATION_PERIOD_TOPIC_PERIOD_ID + "," + ASSOCIATION_PERIOD_TOPIC_TOPIC_ID +"), "
            + " FOREIGN KEY ( " + ASSOCIATION_PERIOD_TOPIC_PERIOD_ID + " ) REFERENCES " + TABLE_PERIOD + " ( " + PERIOD_ID + " ), "
            + " FOREIGN KEY ( " + ASSOCIATION_PERIOD_TOPIC_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " )); ";

    // TABLE_CONTENT
    private static final String TABLE_CONTENT = "content";
    private static final String CONTENT_ID = "id";
    private static final String CONTENT_TOPIC_ID = "topic_id";
    private static final String CREATE_TABLE_CONTENT = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_CONTENT + " ("
            + CONTENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTENT_TOPIC_ID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ( " + CONTENT_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " )); ";

    // TABLE_TEXT_CONTENT
    private static final String TABLE_TEXT = "text";
    private static final String TEXT_ID = "id";
    private static final String TEXT_CONTENT_ID = "content_id";
    private static final String CREATE_TABLE_TEXT = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_TEXT + " ("
            + TEXT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TEXT_CONTENT_ID + " INTEGER ), "
            + " FOREIGN KEY ( " + TEXT_CONTENT_ID + " ) REFERENCES " + TABLE_CONTENT + " ( " + CONTENT_ID + " )); ";

    // TABLE_IMAGE
    private static final String TABLE_IMAGE = "image";
    private static final String IMAGE_ID = "id";
    private static final String IMAGE_CONTENT_ID = "content_id";
    private static final String IMAGE_IMAGE = "image";
    private static final String CREATE_TABLE_IMAGE =  ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_IMAGE + " ("
            + IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IMAGE_CONTENT_ID + " INTEGER ), "
            + IMAGE_IMAGE + " BLOB NOT NULL ), "
            + " FOREIGN KEY ( " + IMAGE_CONTENT_ID + " ) REFERENCES " + TABLE_CONTENT + " ( " + CONTENT_ID + " )); ";

    // TABLE_LANGUAGE
    public static final String TABLE_LANGUAGE = "language";
    private static final String  LANGUAGE_CODE = "code";
    private static final String LANGUAGE_NAME = "name";
    private static final String CREATE_LANGUAGE = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_LANGUAGE + " ("
            + LANGUAGE_CODE + " TEXT PRIMARY KEY, "
            + LANGUAGE_NAME + " TEXT NOT NULL);";

    // TABLE_PERIOD_TR
    private static final String TABLE_PERIOD_TR = "period_tr";
    private static final String PERIOD_TR_PERIOD_ID = "period_id";
    private static final String PERIOD_TR_LANGUAGE_CODE = "language_code";
    private static final String PERIOD_TR_NAME = "name";
    private static final String CREATE_TABLE_PERIOD_TR = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_PERIOD_TR + " ("
            + PERIOD_TR_PERIOD_ID + " INTEGER NOT NULL, "
            + PERIOD_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + PERIOD_TR_NAME + " TEXT NOT NULL, "
            + " PRIMARY KEY(" + PERIOD_TR_PERIOD_ID + "," + PERIOD_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + PERIOD_TR_PERIOD_ID + " ) REFERENCES " + TABLE_PERIOD + " ( " + PERIOD_ID + " ), "
            + " FOREIGN KEY ( " + PERIOD_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";

    // TABLE_TOPIC_TR
    private static final String TABLE_TOPIC_TR = "topic_tr";
    private static final String TOPIC_TR_TOPIC_ID = "topic_id";
    private static final String TOPIC_TR_LANGUAGE_CODE = "language_code";
    private static final String TOPIC_TR_NAME = "name";
    private static final String CREATE_TABLE_TOPIC_TR = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_TOPIC_TR + " ("
            + TOPIC_TR_TOPIC_ID + " INTEGER NOT NULL, "
            + TOPIC_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + TOPIC_TR_NAME + " TEXT NOT NULL, "
            + " PRIMARY KEY(" + TOPIC_TR_TOPIC_ID + "," + TOPIC_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + TOPIC_TR_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " ), "
            + " FOREIGN KEY ( " + TOPIC_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";

    // TABLE_TEXT_TR
    private static final String TABLE_TEXT_TR = "text_tr";
    private static final String TEXT_TR_CONTENT_ID = "content_id";
    private static final String TEXT_TR_LANGUAGE_CODE = "language_code";
    private static final String TEXT_TR_TEXT = "text";
    private static final String CREATE_TABLE_TEXT_TR = ""
            + "CREATE TABLE IF NOT EXISTS" + TABLE_TEXT_TR + " ("
            + TEXT_TR_CONTENT_ID + " INTEGER NOT NULL, "
            + TEXT_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + TEXT_TR_TEXT + " TEXT NOT NULL, "
            + " PRIMARY KEY(" + TEXT_TR_CONTENT_ID + "," + TEXT_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + TEXT_TR_CONTENT_ID + " ) REFERENCES " + TABLE_TEXT + " ( " + TEXT_CONTENT_ID + " ), "
            + " FOREIGN KEY ( " + TEXT_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";



    // CONSTRUCTOR
    public Database (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    private void createTables(){
        List<String> tableNames = Arrays.asList(TABLE_LANGUAGE, TABLE_PERIOD, TABLE_PERIOD_TR, TABLE_TOPIC, TABLE_TOPIC_TR, TABLE_ASSOCIATION_PERIOD_TOPIC, TABLE_CONTENT, TABLE_IMAGE, TABLE_TEXT, TABLE_TEXT_TR );
        List<String> createTablesNames = Arrays.asList(CREATE_LANGUAGE, CREATE_TABLE_PERIOD, CREATE_TABLE_PERIOD_TR, CREATE_TABLE_TOPIC, CREATE_TABLE_TOPIC_TR, CREATE_TABLE_ASSOCIATION_PERIOD_TOPIC, CREATE_TABLE_CONTENT, CREATE_TABLE_IMAGE, CREATE_TABLE_TEXT, CREATE_TABLE_TEXT_TR );
        for (int i = 0; i < tableNames.size(); i++) {
            createTable(db, tableNames.get(i), createTablesNames.get(i));
        }
    }

    // CREATE TABLES
    public void createTable(SQLiteDatabase db, String tableName, String createTable){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            db.execSQL(createTable);
            Log.i("db created:", tableName);
        } catch(Exception e){
            Log.e("db creating",tableName +" fail");
        }
    }

    public void insertMockData(){

        // TABLE_LANGUAGE
        ContentValues languageValues = new ContentValues();
        languageValues.put("de", "Deutsch");
        languageValues.put("en", "English");
        languageValues.put("fr", "Français");
        languageValues.put("nb", "Norsk bokmål");
        db.insert("TABLE_LANGUAGE", null, languageValues);

        // TABLE_PERIOD
        //
        // ABLE_PERIOD_TR
        // TABLE_TOPIC
        // TABLE_TOPIC_TR
        // TABLE_ASSOCIATION_PERIOD_TOPIC
        // TABLE_CONTENT
        // TABLE_IMAGE
        // TABLE_TEXT
        // TABLE_TEXT_TR

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}