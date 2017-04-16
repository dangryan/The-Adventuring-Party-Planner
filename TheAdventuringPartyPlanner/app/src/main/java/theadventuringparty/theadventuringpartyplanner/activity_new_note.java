package theadventuringparty.theadventuringpartyplanner;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_new_note extends AppCompatActivity {

    private NoteDbHelper mHelper;

    EditText titleEdit;
    EditText noteBodyEdit;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        mHelper = new NoteDbHelper(this);

        titleEdit = (EditText) findViewById(R.id.editTitle);
        noteBodyEdit = (EditText) findViewById(R.id.editNoteBody);
        saveButton = (Button)findViewById(R.id.buttonSave);
    }

    public void onSaveButtonClick(View view) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String noteBodyText = String.valueOf(noteBodyEdit.getText());
        String noteTitle = String.valueOf(titleEdit.getText());

        values.put(NoteContract.NoteEntry.COL_NOTE_TITLE, noteTitle);
        values.put(NoteContract.NoteEntry.COL_NOTE_TEXT, noteBodyText);

        db.insertWithOnConflict(NoteContract.NoteEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
        Toast toast = Toast.makeText(this, "Note Added!", Toast.LENGTH_LONG);
        toast.show();
    }
}
