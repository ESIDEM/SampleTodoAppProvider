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

    public void insertNote(Note_Item noteitem) {

        Log.i(LOG_TAG, "Added a Note - " + noteitem.toString());

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add data
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_ROW_TITLE, noteitem.getTitle());
        contentValues.put(NOTE_ROW_DESCRIPTION, noteitem.getDescription());


        // Insert data to table
        db.insert(NOTE_TABLE_NAME, // table name
                null,
                contentValues);

        // Remember to close the db
        db.close();
    }

    public void updateNote(Note_Item note_item, int id) {

        Log.i(LOG_TAG, "Note Updated - ");

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add data
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_ROW_TITLE, note_item.getTitle());
        contentValues.put(NOTE_ROW_DESCRIPTION, note_item.getDescription());
        String [] seletionArgs = {String.valueOf(id)};




        // update data to table
        db.update(NOTE_TABLE_NAME, // select from table name
                contentValues,    //values
                NOTE_ROW_ID +  " LIKE ?", //Where id =
                seletionArgs);


    }

    public ArrayList<Note_Item> getNote_Items() {

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Note_Item> noteItems = new ArrayList<Note_Item>();

        Cursor cursor = db.query(NOTE_TABLE_NAME,
                NoteContract.Note.PROJECTION,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Note_Item noteItem = cursorToNote_Item(cursor);
            noteItems.add(noteItem);
            cursor.moveToNext();
        }

        cursor.close(); // close the cursor
        db.close();     // close the db

        return noteItems;
    }

    /**
     * Convert a cursor to a Note_Item object
     *
     * @param cursor
     * @return
     */
    private Note_Item cursorToNote_Item(Cursor cursor) {

        Note_Item noteItem = new Note_Item();
        noteItem.setId(cursor.getInt(0));
        noteItem.setTitle(cursor.getString(1));
        noteItem.setDescription(cursor.getString(2));


        return noteItem;
    }
}