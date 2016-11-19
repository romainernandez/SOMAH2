package eps.somah2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.util.Base64;

/**
 * Created by Oktay on 11/8/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper mInstance;
    // default image
    public static String image = "0x";
    // Database Info
    private static final String DATABASE_NAME = "SOMAH2.db";
    private static final int DATABASE_VERSION = 2;

    synchronized static public DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: database.execSQL("PRAGMA user_version = " + DATABASE_VERSION);
        // Order matter
        List<String> CREATE_TABLES = Arrays.asList(CREATE_TABLE_LANGUAGE, CREATE_TABLE_PERIOD, CREATE_TABLE_PERIOD_TR, CREATE_TABLE_TOPIC, CREATE_TABLE_TOPIC_TR, CREATE_TABLE_ASSOCIATION_PERIOD_TOPIC, CREATE_TABLE_CONTENT, CREATE_TABLE_IMAGE, CREATE_TABLE_TEXT, CREATE_TABLE_TEXT_TR );
        for (int i = 0; i < CREATE_TABLES.size(); i++) {
            db.execSQL(CREATE_TABLES.get(i));
        }
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // TABLE_PERIOD
    public static final String TABLE_PERIOD = "period";
    private static final String PERIOD_ID = "id";
    private static final String PERIOD_IMAGE = "image";
    private static final String CREATE_TABLE_PERIOD = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_PERIOD +
            " (" +
                PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PERIOD_IMAGE + " BLOB NOT NULL DEFAULT '"+ image +
            "' );";

    // TABLE_TOPIC
    public static final String TABLE_TOPIC = "topic";
    public static final String TOPIC_ID = "id";
    public static final String TOPIC_IMAGE = "image";
    private static final String CREATE_TABLE_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_TOPIC +
            " (" +
                TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TOPIC_IMAGE + " BLOB NOT NULL DEFAULT '"+ image +
            "' );";

    // TABLE_ASSOCIATION_PERIOD_TOPIC
    public static final String TABLE_ASSOCIATION_PERIOD_TOPIC = "association_period_topic";
    public static final String ASSOCIATION_PERIOD_TOPIC_PERIOD_ID = "period_id";
    public static final String ASSOCIATION_PERIOD_TOPIC_TOPIC_ID = "topic_id";
    private static final String CREATE_TABLE_ASSOCIATION_PERIOD_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_ASSOCIATION_PERIOD_TOPIC +
            " (" +
                ASSOCIATION_PERIOD_TOPIC_PERIOD_ID + " INTEGER NOT NULL, " +
                ASSOCIATION_PERIOD_TOPIC_TOPIC_ID + " INTEGER NOT NULL, " +
                    " PRIMARY KEY(" + ASSOCIATION_PERIOD_TOPIC_PERIOD_ID + "," + ASSOCIATION_PERIOD_TOPIC_TOPIC_ID +"), " +
                    " FOREIGN KEY ( " + ASSOCIATION_PERIOD_TOPIC_PERIOD_ID + " ) REFERENCES " + TABLE_PERIOD + " ( " + PERIOD_ID + " ), " +
                    " FOREIGN KEY ( " + ASSOCIATION_PERIOD_TOPIC_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID +
            " )); ";

    // TABLE_CONTENT
    private static final String TABLE_CONTENT = "content";
    private static final String CONTENT_ID = "id";
    private static final String CONTENT_TOPIC_ID = "topic_id";
    private static final String CREATE_TABLE_CONTENT = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_CONTENT + " ("
            + CONTENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTENT_TOPIC_ID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ( " + CONTENT_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " )); ";

    // TABLE_TEXT
    private static final String TABLE_TEXT = "text";
    private static final String TEXT_ID = "id";
    private static final String TEXT_CONTENT_ID = "content_id";
    private static final String CREATE_TABLE_TEXT = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_TEXT + " ("
            + TEXT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TEXT_CONTENT_ID + " INTEGER, "
            + " FOREIGN KEY ( " + TEXT_CONTENT_ID + " ) REFERENCES " + TABLE_CONTENT + " ( " + CONTENT_ID + " )); ";


    // TABLE_IMAGE
    private static final String TABLE_IMAGE = "image";
    private static final String IMAGE_ID = "id";
    private static final String IMAGE_CONTENT_ID = "content_id";
    private static final String IMAGE_IMAGE = "image";
    private static final String CREATE_TABLE_IMAGE = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGE + " ("
            + IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IMAGE_CONTENT_ID + " INTEGER, "
            + IMAGE_IMAGE + " BLOB NOT NULL DEFAULT '"+ image + "', "
            + " FOREIGN KEY ( " + IMAGE_CONTENT_ID + " ) REFERENCES " + TABLE_CONTENT + " ( " + CONTENT_ID + " )); ";

    // TABLE_LANGUAGE
    public static final String TABLE_LANGUAGE = "language";
    public static final String  LANGUAGE_CODE = "code";
    public static final String LANGUAGE_NAME = "name";
    private static final String CREATE_TABLE_LANGUAGE = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_LANGUAGE + " ("
            + LANGUAGE_CODE + " TEXT PRIMARY KEY, "
            + LANGUAGE_NAME + " TEXT NOT NULL);";

    // TABLE_PERIOD_TR
    public static final String TABLE_PERIOD_TR = "period_tr";
    public static final String PERIOD_TR_PERIOD_ID = "period_id";
    public static final String PERIOD_TR_LANGUAGE_CODE = "language_code";
    public static final String PERIOD_TR_NAME = "name";
    private static final String CREATE_TABLE_PERIOD_TR = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_PERIOD_TR + " ("
            + PERIOD_TR_PERIOD_ID + " INTEGER NOT NULL, "
            + PERIOD_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + PERIOD_TR_NAME + " TEXT NOT NULL, "
            + " PRIMARY KEY(" + PERIOD_TR_PERIOD_ID + "," + PERIOD_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + PERIOD_TR_PERIOD_ID + " ) REFERENCES " + TABLE_PERIOD + " ( " + PERIOD_ID + " ), "
            + " FOREIGN KEY ( " + PERIOD_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";

    // TABLE_TOPIC_TR
    public static final String TABLE_TOPIC_TR = "topic_tr";
    public static final String TOPIC_TR_TOPIC_ID = "topic_id";
    public static final String TOPIC_TR_LANGUAGE_CODE = "language_code";
    public static final String TOPIC_TR_NAME = "name";
    private static final String CREATE_TABLE_TOPIC_TR = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_TOPIC_TR + " ("
            + TOPIC_TR_TOPIC_ID + " INTEGER NOT NULL, "
            + TOPIC_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + TOPIC_TR_NAME + " TEXT NOT NULL, "
            + " PRIMARY KEY(" + TOPIC_TR_TOPIC_ID + "," + TOPIC_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + TOPIC_TR_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " ), "
            + " FOREIGN KEY ( " + TOPIC_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";

    // TABLE_TEXT_TR
    private static final String TABLE_TEXT_TR = "text_tr";
    private static final String TEXT_TR_TEXT_ID = "content_id";
    private static final String TEXT_TR_LANGUAGE_CODE = "language_code";
    private static final String TEXT_TR_TEXT = "text";
    private static final String CREATE_TABLE_TEXT_TR = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_TEXT_TR + " ("
            + TEXT_TR_TEXT_ID + " INTEGER NOT NULL, "
            + TEXT_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + TEXT_TR_TEXT + " TEXT NOT NULL DEFAULT '', "
            + " PRIMARY KEY(" + TEXT_TR_TEXT_ID + "," + TEXT_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + TEXT_TR_TEXT_ID + " ) REFERENCES " + TABLE_TEXT + " ( " + TEXT_ID + " ), "
            + " FOREIGN KEY ( " + TEXT_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";



    public ArrayList<String> readTable(String column, String tableName){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT "+ column +" FROM " + tableName;

        if (tableName.equals(TABLE_PERIOD_TR) || tableName.equals(TABLE_TOPIC_TR) || tableName.equals(TABLE_TEXT_TR)) {
            selectQuery += " WHERE language_code = '" + MyApplication.instance.getLanguageCode() + "'";
        }
        Cursor cursor      =  db.rawQuery(selectQuery, null);
        ArrayList<String> data      = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                // get the data into array
                data.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public ArrayList<String> readTable(String columnName, String tableName, String columnName2, String value){
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "SELECT "+ columnName +" FROM " + tableName;
        selectQuery += " WHERE " + columnName2 + " = '" + value + "'";
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> data      = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                // get data into array
                data.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public void updateLanguage(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_LANGUAGE);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_languages.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(LANGUAGE_CODE, (String) obj.get("code"));
                        values.put(LANGUAGE_NAME, (String) obj.get("name"));
                        db.insert(TABLE_LANGUAGE, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updatePeriod(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PERIOD);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_periods.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(PERIOD_ID, (String) obj.get("id"));

                        String base64 = (String) obj.get("image");
                        byte[] decodedBase64 = Base64.decode(base64, Base64.DEFAULT);
                        values.put(PERIOD_IMAGE, decodedBase64);

                        db.insert(TABLE_PERIOD, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updatePeriodTr(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PERIOD_TR);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_periods_trs.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(PERIOD_TR_PERIOD_ID, (String) obj.get("period_id"));
                        values.put(PERIOD_TR_LANGUAGE_CODE, (String) obj.get("language_code"));
                        values.put(PERIOD_TR_NAME, (String) obj.get("name"));
                        db.insert(TABLE_PERIOD_TR, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updateTopic(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PERIOD);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_topics.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(PERIOD_ID, (String) obj.get("id"));

                        String base64 = (String) obj.get("image");
                        byte[] decodedBase64 = Base64.decode(base64, Base64.DEFAULT);
                        values.put(PERIOD_IMAGE, decodedBase64);

                        db.insert(TABLE_PERIOD, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public ArrayList<NamedPeriod> getAllNamedPeriods() {
        ArrayList<NamedPeriod> namedPeriods = new ArrayList<NamedPeriod>();
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = String.format(
            " SELECT %s.%s, %s.%s, %s.%s" +
            " FROM %s INNER JOIN  %s" +
            " ON %s.%s = %s.%s" +
            " WHERE %s.%s = '%s'",
            TABLE_PERIOD_TR, PERIOD_TR_PERIOD_ID, TABLE_PERIOD_TR, PERIOD_TR_NAME, TABLE_PERIOD, PERIOD_IMAGE,
            TABLE_PERIOD_TR, TABLE_PERIOD,
            TABLE_PERIOD_TR, PERIOD_TR_PERIOD_ID, TABLE_PERIOD, PERIOD_ID,
            TABLE_PERIOD_TR, PERIOD_TR_LANGUAGE_CODE, MyApplication.instance.getLanguageCode());

        Cursor cursor      =  db.rawQuery(selectQuery, null);
        Log.d("Romain", "getAllNamedPeriods: cursor.getCount()= " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                NamedPeriod namedPeriod = new NamedPeriod();
                namedPeriod.setId(cursor.getInt(0));
                namedPeriod.setName(cursor.getString(1));
                byte[] bytes = cursor.getBlob(2);
                namedPeriod.setImage(bytes);
                namedPeriods.add(namedPeriod);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return namedPeriods;
    }

    public List<NamedTopic> getAllNamedTopics(String periodId){
        List<NamedTopic> namedTopics = new ArrayList<NamedTopic>();
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = String.format("");

        Cursor cursor      =  db.rawQuery(selectQuery, null);
        Log.d("Romain", "getAllNamedTopics: cursor.getCount()= " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                NamedTopic namedTopic = new NamedTopic();
                // namedTopic.setId(cursor.getInt(0));
                namedTopics.add(namedTopic);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return namedTopics;
    }

        // called if a database upgrade is needed
    private void doUpgrade() {
        // TODO:
    }

    private void setDatabaseVersion() {

    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        // Uncomment for self delete
        // db.execSQL("PRAGMA foreign_keys=ON");
    }
}