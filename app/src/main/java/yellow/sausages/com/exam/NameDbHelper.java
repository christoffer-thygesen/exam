package yellow.sausages.com.exam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import yellow.sausages.com.exam.NameContract.NameEntry;

/**
 * Created by Christoffer on 21/01/2018.
 */

public class NameDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExamExample.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NameEntry.TABLE_NAME + " (" +
                    NameEntry._ID + " INTEGER PRIMARY KEY," +
                    NameEntry.COLUMN_NAME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NameEntry.TABLE_NAME;


    public NameDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
