package ng.codeimpact.sampletodoappdatabase.data;

import android.provider.BaseColumns;

/**
 * Created by Nsikak  Thompson on 4/5/2017.
 */

public class NoteContract implements BaseColumns {

    public static final String DATABASE_NAME = "todo-notes.db";

    /**
     * Define the Note table
     */
    public static final class Note {

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
