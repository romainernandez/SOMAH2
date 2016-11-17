package eps.somah2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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

    public static final String DATABASE_NAME = "SOMAH2.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private boolean insertMockData = true;
    private String languageCode = MyApplication.instance.getLanguageCode();
    public static String image = "0x89504e470d0a1a0a0000000d494844520000006400000064080600000070e29554000000097048597300000b1300000b1301009a9c1800000a4f6943435050686f746f73686f70204943432070726f66696c65000078da9d53675453e9163df7def4424b8880944b6f5215082052428b801491262a2109104a8821a1d91551c1114545041bc8a088038e8e808c15512c0c8a0ad807e421a28e83a3888acafbe17ba36bd6bcf7e6cdfeb5d73ee7acf39db3cf07c0080c9648335135800ca9421e11e083c7c4c6e1e42e40810a2470001008b3642173fd230100f87e3c3c2b22c007be000178d30b0800c04d9bc0301c87ff0fea42995c01808401c07491384b08801400407a8e42a600404601809d98265300a0040060cb6362e300502d0060277fe6d300809df8997b01005b94211501a09100201365884400683b00accf568a450058300014664bc43900d82d00304957664800b0b700c0ce100bb200080c00305188852900047b0060c8232378008499001446f2573cf12bae10e72a00007899b23cb9243945815b082d710757572e1e28ce49172b14366102619a402ec27999193281340fe0f3cc0000a0911511e083f3fd78ce0eaecece368eb60e5f2deabf06ff226262e3fee5cfab70400000e1747ed1fe2c2fb31a803b06806dfea225ee04685e0ba075f78b66b20f40b500a0e9da57f370f87e3c3c45a190b9d9d9e5e4e4d84ac4425b61ca577dfe67c25fc057fd6cf97e3cfcf7f5e0bee22481325d814704f8e0c2ccf44ca51ccf92098462dce68f47fcb70bfffc1dd322c44962b9582a14e35112718e449a8cf332a52289429229c525d2ff64e2df2cfb033edf3500b06a3e017b912da85d6303f64b27105874c0e2f70000f2bb6fc1d4280803806883e1cf77ffef3ffd47a02500806649927100005e44242e54cab33fc708000044a0812ab0411bf4c1182cc0061cc105dcc10bfc6036844224c4c24210420a64801c726029ac82422886cdb01d2a602fd4401d34c051688693700e2ec255b80e3d700ffa61089ec128bc81090441c808136121da8801628a58238e08179985f821c14804128b2420c9881451224b91354831528a542055481df23d720239875c46ba913bc8003282fc86bc47319481b2513dd40cb543b9a8371a8446a20bd06474319a8f16a09bd072b41a3d8c36a1e7d0ab680fda8f3e43c730c0e8180733c46c302ec6c342b1382c099363cbb122ac0cabc61ab056ac03bb89f563cfb17704128145c0093604774220611e4148584c584ed848a8201c243411da093709038451c2272293a84bb426ba11f9c4186232318758482c23d6128f132f107b8843c437241289433227b9900249b1a454d212d246d26e5223e92ca99b34481a2393c9da646bb20739942c202bc885e49de4c3e433e41be421f25b0a9d624071a4f853e22852ca6a4a19e510e534e5066598324155a39a52dda8a15411358f5a42ada1b652af5187a81334759a39cd8316494ba5ada295d31a681768f769afe874ba11dd951e4e97d057d2cbe947e897e803f4770c0d861583c7886728199b18071867197718af984ca619d38b19c754303731eb98e7990f996f55582ab62a7c1591ca0a954a9526951b2a2f54a9aaa6aadeaa0b55f355cb548fa95e537dae46553353e3a909d496ab55aa9d50eb531b5367a93ba887aa67a86f543fa47e59fd890659c34cc34f43a451a0b15fe3bcc6200b6319b3782c216b0dab86758135c426b1cdd97c762abb98fd1dbb8b3daaa9a13943334a3357b352f394663f07e39871f89c744e09e728a797f37e8ade14ef29e2291ba6344cb931655c6baa96979658ab48ab51ab47ebbd36aeeda79da6bd45bb59fb810e41c74a275c2747678fce059de753d953dda70aa7164d3d3af5ae2eaa6ba51ba1bb4477bf6ea7ee989ebe5e809e4c6fa7de79bde7fa1c7d2ffd54fd6dfaa7f5470c5806b30c2406db0cce183cc535716f3c1d2fc7dbf151435dc34043a561956197e18491b9d13ca3d5468d460f8c69c65ce324e36dc66dc6a326062621264b4dea4dee9a524db9a629a63b4c3b4cc7cdcccda2cdd699359b3d31d732e79be79bd79bdfb7605a785a2cb6a8b6b86549b2e45aa659eeb6bc6e855a3959a558555a5db346ad9dad25d6bbadbba711a7b94e934eab9ed667c3b0f1b6c9b6a9b719b0e5d806dbaeb66db67d6167621767b7c5aec3ee93bd937dba7d8dfd3d070d87d90eab1d5a1d7e73b472143a563ade9ace9cee3f7dc5f496e92f6758cf10cfd833e3b613cb29c4699d539bd347671767b97383f3888b894b82cb2e973e2e9b1bc6ddc8bde44a74f5715de17ad2f59d9bb39bc2eda8dbafee36ee69ee87dc9fcc349f299e593373d0c3c843e051e5d13f0b9f95306bdfac7e4f434f8167b5e7232f632f9157add7b0b7a577aaf761ef173ef63e729fe33ee33c37de32de595fcc37c0b7c8b7cb4fc36f9e5f85df437f23ff64ff7affd100a78025016703898141815b02fbf87a7c21bf8e3f3adb65f6b2d9ed418ca0b94115418f82ad82e5c1ad2168c8ec90ad21f7e798ce91ce690e85507ee8d6d00761e6618bc37e0c2785878557863f8e7088581ad131973577d1dc4373df44fa449644de9b67314f39af2d4a352a3eaa2e6a3cda37ba34ba3fc62e6659ccd5589d58496c4b1c392e2aae366e6cbedffcedf387e29de20be37b17982fc85d7079a1cec2f485a716a92e122c3a96404c884e3894f041102aa8168c25f21377258e0a79c21dc267222fd136d188d8435c2a1e4ef2482a4d7a92ec91bc357924c533a52ce5b98427a990bc4c0d4cdd9b3a9e169a76206d323d3abd31839291907142aa214d93b667ea67e66676cbac6585b2fec56e8bb72f1e9507c96bb390ac05592d0ab642a6e8545a28d72a07b267655766bfcd89ca3996ab9e2bcdedccb3cadb90379cef9fffed12c212e192b6a5864b572d1d58e6bdac6a39b23c7179db0ae315052b865606ac3cb88ab62a6dd54fabed5797ae7ebd267a4d6b815ec1ca82c1b5016beb0b550ae5857debdcd7ed5d4f582f59dfb561fa869d1b3e15898aae14db1797157fd828dc78e51b876fcabf99dc94b4a9abc4b964cf66d266e9e6de2d9e5b0e96aa97e6970e6e0dd9dab40ddf56b4edf5f645db2f97cd28dbbb83b643b9a3bf3cb8bc65a7c9cecd3b3f54a454f454fa5436eed2ddb561d7f86ed1ee1b7bbcf634ecd5db5bbcf7fd3ec9bedb5501554dd566d565fb49fbb3f73fae89aae9f896fb6d5dad4e6d71edc703d203fd07230eb6d7b9d4d51dd23d54528fd62beb470ec71fbefe9def772d0d360d558d9cc6e223704479e4e9f709dff71e0d3ada768c7bace107d31f761d671d2f6a429af29a469b539afb5b625bba4fcc3ed1d6eade7afc47db1f0f9c343c59794af354c969dae982d39367f2cf8c9d959d7d7e2ef9dc60dba2b67be763cedf6a0f6fefba1074e1d245ff8be73bbc3bce5cf2b874f2b2dbe51357b8579aaf3a5f6dea74ea3cfe93d34fc7bb9cbb9aaeb95c6bb9ee7abdb57b66f7e91b9e37ceddf4bd79f116ffd6d59e393dddbdf37a6ff7c5f7f5df16dd7e7227fdcecbbbd97727eeadbc4fbc5ff440ed41d943dd87d53f5bfedcd8efdc7f6ac077a0f3d1dc47f7068583cffe91f58f0f43058f998fcb860d86eb9e383e3939e23f72fde9fca743cf64cf269e17fea2fecbae17162f7ef8d5ebd7ced198d1a197f29793bf6d7ca5fdeac0eb19afdbc6c2c61ebec97833315ef456fbedc177dc771defa3df0f4fe47c207f28ff68f9b1f553d0a7fb93199393ff040398f3fc63332ddb000000206348524d00007a25000080830000f9ff000080e9000075300000ea6000003a980000176f925fc546000004bb4944415478daec9ddf4f5b6518c7bf180a7414ca4a0bdd4e2d0ce88694b4ae61e14788ebd4e8c5e608cb12e6d00b63a277de706982fb1b745e6d26f34292c56589c9e28d256171fca841483b7e650564b5856e9cb6830e11c1cc2bb0b00e68a1ed5bf87eeeda73f2bee7e493e77d9ef7bcef69b36e7cf8f30b1c009e2d070fc26de035100a2114422184422884500885100aa110422184422884500885100aa110422114422884500885100aa1104221144228e490929da9179ea756a0a4ba68e373f15adea6e3530ffc14924c4acd4590ac5a1caf2e86462a448e72874bef0056965721fbc2f08e0530333407bf7b5ef8fbcc1279f77b9e5a819af36538d964804aa3dc737bb22f8c91fb5318bc3341218988a87de7c4ce9190a0985f6efc2664c40827c458af43d3c7e67d89889de8f96150b868112a87d4b694a1a1ad2665fdd9dbeb00402829c294bd67bfb0a45446b414c9a2a390adc394a9414a5bff6f7d749a42a2f13ae7e11948dfbc413295081325c20c59ceefc711f247d2d67fb9ed18937a347f2faca2fb9b61b47cd5b46da9fb3cb48cb9472144e4bf204f2d6c7cbfb4168152958bd24a0daa6cafa340931fdfb059a3472fdc1412cd827709fd5da338fba9f5a5639e013ffe70cec1eb8c3d77587f2d7a04d3e8c620ea2e57a3f19215b94a051f9dec054ff72cb427d430bf5d0e0008f923e8bd358227a3cfe26a67f0ce04c281085a3bce51c85ee9bf398e62632182de45f4df1c4fb89da9077ea00314b21fdceb74a6b6d21b0bb0ca4a367597ab777dee78cf0c232469f30a8b0ed6f74da869aad8d5f9c38e0984661629245e4acd4538d3762ae6b17f5fac2157a980d67034ae36655f187d5d0f9943122157a580be4ab36fedc9be306e773ab01c5ea1907433ec98405fd743a1641c4a21d32e1f5c0e8fb06beed914412169a1c26a4085d580c8274b18f9750abdb7dc425ee7a1db9755a0c947638b059f7fd78aca6689424412d3da712eaec92385a4007b7b9d5052322a87ac3c5f456032f4ca89e13a5ac3d1b81ebbdbdbeb30f72828c4b6a0ac83fa33b1924587fa4b6654580dbbaec2ee5eebe190952cfcee79dcbdd68369976fd75598a6bc9042928dcbe1d9f5b9c7abb514128b3cb50257aedb516a2e4a69bf6abd8a4262f16e870d2a8d121f7cd908dbd5aa3db5a5af2acea888164e88ed6ad5a627bab60b265cb96e476d4b59dc6d55364bb0bdf746460911aaec35d6eb60bb607ae97b95468986b61ad82e9ec463d713cc8eca78ea59c0827729663bf12e50ad13980c524874deb07ff6e6b6e7e428b3616a9036b69dfeb3bc86907f71d33c443295247c0db3a3328544e78d78df05c95166efdb82d5b4cb27c4da881039646bde10bd3c3ef0420ab447d2daffb063429875122184dcffda9db6ddefb22f8cee6f0759f6c69232742fb5c386dff314b73b1d2c7b5fc550d724fc2e1967da4e253da7f4ffe41672d550d8a7bdc67a1d2ce72bf65dcc58df34067e1c1166639cd011128dd7390faf731e6a633e2a9b8fa1fcb41e1aa920e13ce1f9fd4f8cf7cc082b42f808d92e72b4956a0080d658889c238a4d1343e0ff8dd381c920664765e1f65e656484ec14395be1ff18120aa110422114422884500885100aa110422114422884420885100aa1104221144228844208855008a110422114422824c3f96f00058088f05d0fb0800000000049454e44ae426082";

    // TABLE_PERIOD
    public static final String TABLE_PERIOD = "period";
    private static final String PERIOD_ID = "id";
    private static final String PERIOD_IMAGE = "image";

    private static final String CREATE_TABLE_PERIOD = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_PERIOD+" ("
            + PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PERIOD_IMAGE + " BLOB NOT NULL DEFAULT '"+ image + "' );";

    // TABLE_TOPIC
    public static final String TABLE_TOPIC = "topic";
    public static final String TOPIC_ID = "id";
    public static final String TOPIC_IMAGE = "image";
    private static final String CREATE_TABLE_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_TOPIC + " ("
            + TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TOPIC_IMAGE + " BLOB NOT NULL DEFAULT '"+ image + "' );";

    // TABLE_ASSOCIATION_PERIOD_TOPIC
    public static final String TABLE_ASSOCIATION_PERIOD_TOPIC = "association_period_topic";
    private static final String ASSOCIATION_PERIOD_TOPIC_PERIOD_ID = "period_id";
    private static final String ASSOCIATION_PERIOD_TOPIC_TOPIC_ID = "topic_id";
    private static final String CREATE_TABLE_ASSOCIATION_PERIOD_TOPIC = ""
            + "CREATE TABLE IF NOT EXISTS " + TABLE_ASSOCIATION_PERIOD_TOPIC + " ("
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
    private static final String CREATE_LANGUAGE = ""
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

    // CONSTRUCTOR
    public Database (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
        createTables();
        if (insertMockData)
                insertMockData();
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
            Log.d("Romain","created = "+tableName);
        } catch(Exception e){
            Log.d("Romain","failed to create = "+tableName +e.getMessage());
        }
    }

    public void insertMockData(){

        // TABLE_LANGUAGE
        String[] allLanguageCode = {"de", "en", "fr", "nb"};
        String[] allLanguageName = {"Deutsch", "English", "Français", "Norsk bokmål"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allLanguageCode.length; i++) {
                ContentValues values = new ContentValues();
                values.put(LANGUAGE_CODE, allLanguageCode[i]);
                values.put(LANGUAGE_NAME, allLanguageName[i]);
                db.insert(TABLE_LANGUAGE, null, values);
            }
        db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_PERIOD

        String allPeriodId[] = {"1", "2"};
        String allPeriodImage[] = {image, image};

        try{
            db.beginTransaction();
            for (int i = 0; i < allPeriodId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(PERIOD_ID, allPeriodId[i]);
                values.put(PERIOD_IMAGE, allPeriodImage[i]);
                db.insert(TABLE_PERIOD, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_PERIOD_TR

        String allPeriodTrPeriodId[] = {"1", "1", "2", "2"};
        String allPeriodTrLanguageCode[] = {"en", "fr", "en", "fr"};
        String allPeriodTrNames[] = {"Pregnancy", "Grossesse", "0-2 weeks", "0-2 semaines"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allPeriodTrPeriodId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(PERIOD_TR_PERIOD_ID, allPeriodTrPeriodId[i]);
                values.put(PERIOD_TR_LANGUAGE_CODE, allPeriodTrLanguageCode[i]);
                values.put(PERIOD_TR_NAME, allPeriodTrNames[i]);
                db.insert(TABLE_PERIOD_TR, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
            Log.d("Romain", e.getMessage());
        } finally {
            db.endTransaction();
        }

        // TABLE_TOPIC

        String allTopicId[] = {"1", "2"};
        String allTopicImage[] = {image, image};

        try{
            db.beginTransaction();
            for (int i = 0; i < allTopicId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(TOPIC_ID, allTopicId[i]);
                values.put(TOPIC_IMAGE, allTopicImage[i]);
                db.insert(TABLE_TOPIC, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_TOPIC_TR

        String allTopicTrTopicId[] = {"1", "1", "2", "2"};
        String allTopicTrLanguageCode[] = {"en", "fr", "en", "fr"};
        String allTopicTrNames[] = {"Why", "Quoi", "How", "Que"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allTopicTrTopicId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(TOPIC_TR_TOPIC_ID, allTopicTrTopicId[i]);
                values.put(TOPIC_TR_LANGUAGE_CODE, allTopicTrLanguageCode[i]);
                values.put(TOPIC_TR_NAME, allTopicTrNames[i]);
                db.insert(TABLE_TOPIC_TR, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        }
        finally {
            db.endTransaction();
        }

        // TABLE_ASSOCIATION_PERIOD_TOPIC

        String allAssocationPeriodTopicPeriodId[] = {"1", "1", "2"};
        String allAssocationPeriodTopicTopicId[] = {"1", "2", "1"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allAssocationPeriodTopicPeriodId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(ASSOCIATION_PERIOD_TOPIC_PERIOD_ID, allAssocationPeriodTopicPeriodId[i]);
                values.put(ASSOCIATION_PERIOD_TOPIC_TOPIC_ID, allAssocationPeriodTopicTopicId[i]);
                db.insert(TABLE_ASSOCIATION_PERIOD_TOPIC, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_CONTENT

        String allContentId[] = {"1", "2"};
        String allContentTopicId[] = {"1", "2"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allContentId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(CONTENT_ID, allContentId[i]);
                values.put(CONTENT_TOPIC_ID, allContentTopicId[i]);
                db.insert(TABLE_CONTENT, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_IMAGE

        String allImageId[] = {"1", "2"};
        String allImageContentId[] = {"1", "2"};
        String allImageImage[] = {image, image};

        try{
            db.beginTransaction();
            for (int i = 0; i < allImageId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(IMAGE_ID, allImageId[i]);
                values.put(IMAGE_CONTENT_ID, allImageContentId[i]);
                values.put(IMAGE_IMAGE, allImageImage[i]);
                db.insert(TABLE_IMAGE, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_TEXT

        String allTextId[] = {"1", "2"};
        String allTextContentId[] = {"1", "2"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allTextId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(TEXT_ID, allTextId[i]);
                values.put(TEXT_CONTENT_ID, allTextContentId[i]);
                db.insert(TABLE_TEXT, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }

        // TABLE_TEXT_TR
        String allTextTrTextId[] = {"1", "1", "2", "2"};
        String allTextTrLanguageCode[] = {"en", "fr", "en", "fr"};
        String allTextTrText[] = {"AHA", "OHO", "UHU", "IHI"};

        try{
            db.beginTransaction();
            for (int i = 0; i < allTextTrTextId.length; i++) {
                ContentValues values = new ContentValues();
                values.put(TEXT_TR_TEXT_ID, allTextTrTextId[i]);
                values.put(TEXT_TR_LANGUAGE_CODE, allTextTrLanguageCode[i]);
                values.put(TEXT_TR_TEXT, allTextTrText[i]);
                db.insert(TABLE_TEXT_TR, null, values);
            }
            db.setTransactionSuccessful();} catch (SQLException e) {
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<String> readTable(String column, String tableName){
        String selectQuery = "SELECT "+ column +" FROM " + tableName;

        if (tableName.equals(TABLE_PERIOD_TR) || tableName.equals(TABLE_TOPIC_TR) || tableName.equals(TABLE_TEXT_TR)) {
            selectQuery += " WHERE language_code = '" + languageCode + "'";
        }

        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> data      = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
                data.add(cursor.getString(0));
                Log.d("Romain getString(0)", cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}