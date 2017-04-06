package ng.codeimpact.sampletodoappdatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ng.codeimpact.sampletodoappdatabase.data.NoteDbHelper;
import ng.codeimpact.sampletodoappdatabase.model.Note_Item;

import static android.R.attr.id;
import static android.R.attr.mode;

public class AddActivity extends AppCompatActivity {
    private final static String LOG_TAG = AddActivity.class.getSimpleName();
    private NoteDbHelper mDbHelper;
    private EditText add_title, add_description;
    String title_txt;
    String description_txt;
    boolean isUpdate;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add_title = (EditText) findViewById(R.id.input_title);
        add_description = (EditText) findViewById(R.id.input_description);

        //getting a reference to the DbHelper
        mDbHelper = new NoteDbHelper(this);

        isUpdate = getIntent().getBooleanExtra("update", false);

        if (isUpdate) {
            String extra_title = getIntent().getStringExtra("note_title");
            String extra_description = getIntent().getStringExtra("note_desc");
            id = getIntent().getIntExtra("id",0);
            add_title.setText(extra_title);
            add_description.setText(extra_description);
        }


    }

    // Update note
    private void updateNote() {

        title_txt = add_title.getText().toString();
        description_txt = add_description.getText().toString();
        if (!dataValidated(title_txt, description_txt)) {
            return;
        }
        mDbHelper.updateNote(new Note_Item(title_txt, description_txt), id);
        Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddActivity.this, MainActivity.class));

    }

    // Add new Notes
    public void addNote() {

        title_txt = add_title.getText().toString();
        description_txt = add_description.getText().toString();
        if (!dataValidated(title_txt, description_txt)) {
            return;
        }

        mDbHelper.insertNote(new Note_Item(title_txt, description_txt));
        Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddActivity.this, MainActivity.class));

    }

    //Input Validation
    boolean dataValidated(String email, String password) {
        if (email.isEmpty()) {
            add_title.setError("Enter a title");
            return false;
        }
        if (password.isEmpty()) {
            add_description.setError("Enter description");
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            if (isUpdate) {
                updateNote();

            } else {
                addNote();

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
