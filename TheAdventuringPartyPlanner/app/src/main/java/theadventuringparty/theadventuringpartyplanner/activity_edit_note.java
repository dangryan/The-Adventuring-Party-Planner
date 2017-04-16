package theadventuringparty.theadventuringpartyplanner;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class activity_edit_note extends AppCompatActivity {

    private NoteDbHelper mHelper = new NoteDbHelper(this);

    EditText titleEdit;
    EditText bodyEdit;
    Button deleteButton;

    Spinner prioritySpin;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        titleEdit = (EditText) findViewById(R.id.editTitle);
        bodyEdit = (EditText) findViewById(R.id.editNoteBody);

        Bundle extras = getIntent().getExtras();

        final String noteTitleText = extras.getString("note title");
        String noteBodyText = extras.getString("note body text");

        titleEdit.setText(noteTitleText);
        bodyEdit.setText(noteBodyText);
    }


    public void onSaveButtonClickEdit(View view) {

        Bundle extras = getIntent().getExtras();
        String noteTitleOld = extras.getString("note title");

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String noteBodyText = String.valueOf(bodyEdit.getText());
        String notesTitleText = String.valueOf(titleEdit.getText());

        values.put(NoteContract.NoteEntry.COL_NOTE_TITLE, notesTitleText);
        values.put(NoteContract.NoteEntry.COL_NOTE_TEXT, noteBodyText);

        db.update(
                NoteContract.NoteEntry.TABLE,
                values,
                NoteContract.NoteEntry.COL_NOTE_TITLE + "= '" + noteTitleOld + "'",
                null);

        db.close();
        Toast toast = Toast.makeText(this, "Note updated!", Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

    public void deleteNote(View view) {
        Bundle extras = getIntent().getExtras();
        String noteTitle = extras.getString("note title");

        mHelper = new NoteDbHelper(getApplicationContext());
        SQLiteDatabase db = mHelper.getWritableDatabase();

        db.delete(NoteContract.NoteEntry.TABLE,"title = '" + noteTitle + "'",null);
        db.close();

        finish();
    }
}