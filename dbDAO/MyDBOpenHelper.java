package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bakhah on 24/03/17.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_ITEM = "item";

    public static final String CREATE_DB = "CREATE TABLE " + TABLE_ITEM +
            " (" + ItemEnum.COL_ID.getName() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ItemEnum.COL_NAME.getName() + " TEXT NOT NULL);";

    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_ITEM + ";");
        onCreate(db);
    }
}
