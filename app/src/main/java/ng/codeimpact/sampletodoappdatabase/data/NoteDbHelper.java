package ng.codeimpact.sampletodoappdatabase.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



/**
 * Created by Nsikak  Thompson on 4/5/2017.
 */

public class NoteDbHelper extends SQLiteOpenHelper {

    private final static String LOG_TAG = NoteDbHelper.class.getSimpleName();

    // Database name
    private final static String DB_NAME = NoteContract.DATABASE_NAME;

    // Database Note
    private final static int DB_NOTE = 1;

    // Note table
    private final static String NOTE_TABLE_NAME = NoteContract.Note.TABLE_NAME;
    private final static String NOTE_ROW_ID = NoteContract.Note.ID;
    public final static String NOTE_ROW_TITLE = NoteContract.Note.NOTE_TITLE;
    public final static String NOTE_ROW_DESCRIPTION = NoteContract.Note.DESCRIPTION;


    // SQL statement to create the Version table
    private final static String NOTE_TABLE_CREATE =
            "CREATE TABLE " +
                    NOTE_TABLE_NAME + " (" +
                    NOTE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    NOTE_ROW_TITLE + " TEXT, " +
                    NOTE_ROW_DESCRIPTION + " TEXT " + ");";

    public NoteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_NOTE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Version table
        db.execSQL(NOTE_TABLE_CREATE);
        Log.i(LOG_TAG, "Creating table with query: " + NOTE_TABLE_CREATE);

        //Inserting initial values
        ContentValues values = new ContentValues();
        values.put(NOTE_ROW_TITLE, "GDG Uyo Meet Up 2017");
        values.put(NOTE_ROW_DESCRIPTION,
                "We will be discussing Android Local Storage with include " +
                        "Share Preference, SQLite and Content " +
                "Providers and how you can use them in building great Apps.");

        db.insert(NOTE_TABLE_NAME, null , values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE_NAME);
        onCreate(db);

    }

}