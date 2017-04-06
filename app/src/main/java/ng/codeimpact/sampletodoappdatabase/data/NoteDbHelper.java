package ng.codeimpact.sampletodoappdatabase.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ng.codeimpact.sampletodoappdatabase.model.Note_Item;

import static android.R.attr.id;

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
    private final static String NOTE_ROW_TITLE = NoteContract.Note.NOTE_TITLE;
    private final static String NOTE_ROW_DESCRIPTION = NoteContract.Note.DESCRIPTION;


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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE_NAME);
        onCreate(db);

    }

}