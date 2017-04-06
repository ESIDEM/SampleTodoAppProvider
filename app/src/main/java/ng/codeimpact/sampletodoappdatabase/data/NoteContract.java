package ng.codeimpact.sampletodoappdatabase.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Nsikak  Thompson on 4/5/2017.
 */

public class NoteContract  {

    public static final String DATABASE_NAME = "todo-notes.db";


    // Name of the Content Provider, use package name by convention so that it's unique on device
    public static final String CONTENT_AUTHORITY = "ng.codeimpact.sampletodoappdatabase";

    // A path that points to the note table
    public static final String PATH_NOTE = "note";

    // Construct the Base Content Uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Define the Note table
     */
    public static final class Note implements BaseColumns {


        // Content Uri = Content Authority + Path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTE).build();

        // Use MIME type prefix android.cursor.dir/ for returning multiple items
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/ng.codeimpact.sampletodoappdatabase.notes";
        // Use MIME type prefix android.cursor.item/ for returning a single item
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/ng.codeimpact.sampletodoappdatabase.note";


        // Define table name
        public static final String TABLE_NAME = "note";

        // Define table columns
        public static final String ID = BaseColumns._ID;
        public static final String NOTE_TITLE = "note_title";
        public static final String DESCRIPTION = "description";


        // Define projection for Version table
        public static final String[] PROJECTION = new String[] {
                /*0*/ NoteContract.Note.ID,
                /*1*/ NoteContract.Note.NOTE_TITLE,
                /*2*/ NoteContract.Note.DESCRIPTION,

        };
    }



}
