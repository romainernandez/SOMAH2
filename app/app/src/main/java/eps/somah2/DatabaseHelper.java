package eps.somah2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oktay on 11/8/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance;

    // Database Info
    private static final String DATABASE_NAME = "somah2_app.db";
    private static final int DATABASE_VERSION = 2;

    public synchronized static  DatabaseHelper getInstance(Context context) {
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
        // Order matter
        List<String> CREATE_TABLES = Arrays.asList(CREATE_TABLE_LANGUAGE, CREATE_TABLE_PERIOD, CREATE_TABLE_PERIOD_TR, CREATE_TABLE_TOPIC, CREATE_TABLE_TOPIC_TR, CREATE_TABLE_ASSOCIATION_PERIOD_TOPIC, CREATE_TABLE_CONTENT, CREATE_TABLE_CONTENT_TR);
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
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        List<String> TABLES = Arrays.asList(TABLE_LANGUAGE, TABLE_PERIOD, TABLE_PERIOD_TR, TABLE_TOPIC, TABLE_TOPIC_TR, TABLE_ASSOCIATION_PERIOD_TOPIC, TABLE_CONTENT, TABLE_CONTENT_TR);
        Collections.reverse(TABLES);
        for (int i = 0; i < TABLES.size(); i++) {
            db.execSQL("DELETE FROM " + TABLES.get(i));
        }
        this.updateLanguage();
        this.updatePeriod();
        this.updatePeriodTr();
        this.updateTopic();
        this.updateTopicTr();
        this.updateAssociationPeriodTopic();
        this.updateContent();
        this.updateContentTr();
    }

    // TABLE_PERIOD
    public static final String TABLE_PERIOD = "period";
    public static final String PERIOD_ID = "id";
    public static final String PERIOD_IMAGE = "image";
    private static final String CREATE_TABLE_PERIOD = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_PERIOD +
            " (" +
                PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PERIOD_IMAGE + " BLOB );";

    // TABLE_TOPIC
    public static final String TABLE_TOPIC = "topic";
    public static final String TOPIC_ID = "id";
    public static final String TOPIC_IMAGE = "image";
    private static final String CREATE_TABLE_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_TOPIC +
            " (" +
                TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TOPIC_IMAGE + " BLOB );";

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
            + TOPIC_TR_TOPIC_ID + " INTEGER, "
            + TOPIC_TR_LANGUAGE_CODE + " TEXT NOT NULL, "
            + TOPIC_TR_NAME + " TEXT NOT NULL, "
            + " PRIMARY KEY(" + TOPIC_TR_TOPIC_ID + "," + TOPIC_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + TOPIC_TR_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " ), "
            + " FOREIGN KEY ( " + TOPIC_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";

    // TABLE_CONTENT
    public static final String TABLE_CONTENT = "content";
    public static final String CONTENT_ID = "id";
    public static final String CONTENT_TOPIC_ID = "topic_id";
    public static final String CONTENT_IMAGE = "image";

    private static final String CREATE_TABLE_CONTENT = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_CONTENT +
            " (" +
            CONTENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CONTENT_TOPIC_ID + " INTEGER, " +
            CONTENT_IMAGE + " BLOB, " +
            " FOREIGN KEY ( " + CONTENT_TOPIC_ID + " ) REFERENCES " + TABLE_TOPIC + " ( " + TOPIC_ID + " )); ";

    // TABLE_CONTENT_TR
    public static final String TABLE_CONTENT_TR = "content_tr";
    public static final String CONTENT_TR_CONTENT_ID = "content_id";
    public static final String CONTENT_TR_LANGUAGE_CODE = "language_code";
    public static final String CONTENT_TR_TEXT = "text";
    public static final String CONTENT_TR_TITLE = "title";
    private static final String CREATE_TABLE_CONTENT_TR = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_CONTENT_TR +
            " (" +
            CONTENT_TR_CONTENT_ID + " INTEGER, " +
            CONTENT_TR_LANGUAGE_CODE + " INTEGER, " +
            CONTENT_TR_TEXT + " TEXT, " +
            CONTENT_TR_TITLE + " TEXT, "
            + " PRIMARY KEY(" + CONTENT_TR_CONTENT_ID + "," + CONTENT_TR_LANGUAGE_CODE +"), "
            + " FOREIGN KEY ( " + CONTENT_TR_CONTENT_ID + " ) REFERENCES " + TABLE_CONTENT + " ( " + CONTENT_ID + " ), "
            + " FOREIGN KEY ( " + CONTENT_TR_LANGUAGE_CODE + " ) REFERENCES " + TABLE_LANGUAGE + " ( " + LANGUAGE_CODE + " )); ";

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
                        // JSON answer is a string
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
        // db.execSQL("DELETE FROM " + TABLE_PERIOD);

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
        //db.execSQL("DELETE FROM " + TABLE_PERIOD_TR);

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
                        values.put(TOPIC_ID, (String) obj.get("id"));

                        Log.d("Romain", (String) obj.get("id"));

                        String base64 = (String) obj.get("image");
                        byte[] decodedBase64 = Base64.decode(base64, Base64.DEFAULT);
                        values.put(TOPIC_IMAGE, decodedBase64);

                        db.insert(TABLE_TOPIC, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updateTopicTr(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PERIOD_TR);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_topics_trs.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(TOPIC_TR_TOPIC_ID, (String) obj.get("topic_id"));
                        values.put(TOPIC_TR_LANGUAGE_CODE, (String) obj.get("language_code"));
                        values.put(TOPIC_TR_NAME, (String) obj.get("name"));
                        db.insert(TABLE_TOPIC_TR, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updateAssociationPeriodTopic(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PERIOD_TR);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_associations_period_topic.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(ASSOCIATION_PERIOD_TOPIC_TOPIC_ID, (String) obj.get("topic_id"));
                        values.put(ASSOCIATION_PERIOD_TOPIC_PERIOD_ID, (String) obj.get("period_id"));
                        db.insert(TABLE_ASSOCIATION_PERIOD_TOPIC, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updateContent(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PERIOD_TR);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_contents.php";

        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(CONTENT_ID, (String) obj.get("id"));
                        values.put(CONTENT_TOPIC_ID, (String) obj.get("topic_id"));

                        String base64 = (String) obj.get("image");
                        byte[] decodedBase64 = Base64.decode(base64, Base64.DEFAULT);
                        values.put(CONTENT_IMAGE, decodedBase64);

                        db.insert(TABLE_CONTENT, null, values);
                    }
                    db.setTransactionSuccessful();
                } catch (Exception e) { Log.d("Romain", "onSuccess: Exception= " + e.getMessage() ); }
                finally {
                    db.endTransaction();
                }
            }
        });
    }

    public void updateContentTr(){
        // Delete local rows
        final SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_PERIOD_TR);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String url = MyApplication.instance.getServerUrl() +"web/get_all_contents_trs.php";
        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray arr = new JSONArray(response);
                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = (JSONObject) arr.get(i);
                        ContentValues values = new ContentValues();
                        values.put(CONTENT_TR_CONTENT_ID, (String) obj.get("content_id"));
                        values.put(CONTENT_TR_LANGUAGE_CODE, (String) obj.get("language_code"));
                        values.put(CONTENT_TR_TITLE, (String) obj.get("title"));
                        values.put(CONTENT_TR_TEXT, (String) obj.get("text"));
                        db.insert(TABLE_CONTENT_TR, null, values);
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
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = String.format(
            "SELECT %s.%s, %s.%s, %s.%s " +
            "FROM %s INNER JOIN %s " +
            "ON %s.%s = %s.%s " +
            "WHERE %s.%s = '%s' " +
            "ORDER BY %s.%s",
            TABLE_PERIOD_TR, PERIOD_TR_PERIOD_ID, TABLE_PERIOD_TR, PERIOD_TR_NAME, TABLE_PERIOD, PERIOD_IMAGE,
            TABLE_PERIOD_TR, TABLE_PERIOD,
            TABLE_PERIOD_TR, PERIOD_TR_PERIOD_ID, TABLE_PERIOD, PERIOD_ID,
            TABLE_PERIOD_TR, PERIOD_TR_LANGUAGE_CODE, MyApplication.instance.getLanguageCode(),
            TABLE_PERIOD_TR, PERIOD_TR_PERIOD_ID);

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

    public List<NamedTopic> getAllNamedTopics(int periodId){
        List<NamedTopic> namedTopics = new ArrayList<NamedTopic>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = String.format(
                "SELECT %s.%s, %s.%s, %s.%s " +
                "FROM %s " +
                "INNER JOIN %s " +
                    "ON %s.%s = %s.%s " +
                "INNER JOIN %s " +
                    "ON %s.%s = %s.%s " +
                "WHERE %s.%s = '%s' " +
                "AND %s.%s = '%s' " +
                "ORDER BY %s.%s",
                TABLE_TOPIC, TOPIC_ID, TABLE_TOPIC_TR, TOPIC_TR_NAME, TABLE_TOPIC, TOPIC_IMAGE,
                TABLE_TOPIC,
                TABLE_TOPIC_TR,
                    TABLE_TOPIC, TOPIC_ID, TABLE_TOPIC_TR, TOPIC_TR_TOPIC_ID,
                TABLE_ASSOCIATION_PERIOD_TOPIC,
                    TABLE_TOPIC, TOPIC_ID, TABLE_ASSOCIATION_PERIOD_TOPIC, ASSOCIATION_PERIOD_TOPIC_TOPIC_ID,
                TABLE_ASSOCIATION_PERIOD_TOPIC, ASSOCIATION_PERIOD_TOPIC_PERIOD_ID, periodId,
                TABLE_TOPIC_TR, TOPIC_TR_LANGUAGE_CODE, MyApplication.instance.getLanguageCode(),
                TABLE_TOPIC, TOPIC_ID);

        Cursor cursor      =  db.rawQuery(selectQuery, null);
        Log.d("Romain", "getAllNamedTopics: cursor.getCount()= " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                NamedTopic namedTopic = new NamedTopic();
                namedTopic.setId(cursor.getInt(0));
                namedTopic.setName(cursor.getString(1));
                byte[] bytes = cursor.getBlob(2);
                namedTopic.setImage(bytes);
                namedTopics.add(namedTopic);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return namedTopics;
    }

    public ArrayList<TextedContent> getAllTextedContents(int topicId) {
        ArrayList<TextedContent> textedContents = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = String.format(
                "SELECT %s.%s, %s.%s, %s.%s, %s.%s " +
                "FROM %s INNER JOIN %s " +
                    "ON %s.%s = %s.%s " +
                "WHERE %s.%s = '%s' " +
                "AND %s.%s = '%s' " +
                "ORDER BY %s.%s",
                TABLE_CONTENT, CONTENT_ID, TABLE_CONTENT, CONTENT_IMAGE, TABLE_CONTENT_TR, CONTENT_TR_TEXT, TABLE_CONTENT_TR, CONTENT_TR_TITLE,
                TABLE_CONTENT, TABLE_CONTENT_TR,
                    TABLE_CONTENT, CONTENT_ID, TABLE_CONTENT_TR, CONTENT_TR_CONTENT_ID,
                TABLE_CONTENT_TR, CONTENT_TR_LANGUAGE_CODE, MyApplication.instance.getLanguageCode(),
                TABLE_CONTENT, CONTENT_TOPIC_ID, topicId,
                TABLE_CONTENT, CONTENT_ID);
        Cursor cursor      =  db.rawQuery(selectQuery, null);

        Log.d("Romain", "getAllTextedContents: cursor.getCount()= " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                TextedContent textedContent = new TextedContent();
                textedContent.setId(cursor.getInt(0));
                byte[] bytes = cursor.getBlob(1);
                textedContent.setImage(bytes);
                textedContent.setText(cursor.getString(2));
                textedContent.setTitle(cursor.getString(3));
                textedContents.add(textedContent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return textedContents;
    }

    public ArrayList<String> getAllLanguagesNames () {
        ArrayList<String> languagesNames = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = String.format(
                "SELECT %s " +
                "FROM %s " +
                "ORDER BY %s",
                LANGUAGE_NAME,
                TABLE_LANGUAGE,
                LANGUAGE_NAME);
        Cursor cursor      =  db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                languagesNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return languagesNames;
    }

    public String getLanguageCode (String languageName) {
        String languageCode = new String();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = String.format(
                "SELECT %s " +
                "FROM %s " +
                "WHERE %s = '%s'",
                LANGUAGE_CODE,
                TABLE_LANGUAGE,
                LANGUAGE_NAME, languageName);
        Cursor cursor      =  db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                languageCode = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return languageCode;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        // Uncomment for self delete
        // db.execSQL("PRAGMA foreign_keys=ON");
    }
}
