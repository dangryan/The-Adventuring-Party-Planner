package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tab4Notes extends Fragment{
    private NoteDbHelper mHelper;
    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notes, container, false);
        mTaskListView = (ListView)rootView.findViewById(R.id.noteListView);

        updateNoteTitle();

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateNoteTitle();
    }

    private void updateNoteTitle() {
        //section to update the Title
        ArrayList<String> noteNameList = new ArrayList<>();

        mHelper = new NoteDbHelper(getContext());
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String whereClause = null;

        Cursor cursor = db.query(
                NoteContract.NoteEntry.TABLE,
                new String[]{NoteContract.NoteEntry.COL_NOTE_TITLE, NoteContract.NoteEntry.COL_NOTE_TEXT},
                whereClause,
                null,
                null,
                null,
                null);


        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(NoteContract.NoteEntry.COL_NOTE_TITLE);
            noteNameList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getContext(),
                    R.layout.note_display_layout,
                    //adds data to TextView
                    R.id.noteTitleLabel,
                    noteNameList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(noteNameList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    public void editNote(View view) {
        View parent = (View)view.getParent();
        TextView noteTextView = (TextView) parent.findViewById(R.id.noteTitleLabel);
        String noteTitle = noteTextView.getText().toString();
        mHelper = new NoteDbHelper(getContext());
        SQLiteDatabase db = mHelper.getWritableDatabase();

        String table = NoteContract.NoteEntry.TABLE;
        String[] columns = new String[] { "title", "text"};
        String selection = NoteContract.NoteEntry.COL_NOTE_TITLE+ "= '" + noteTitle + "'";


        Cursor cursor2 = db.query(table, columns, selection,null,null,null,null,null);

        cursor2.moveToFirst();

        String noteTitleText = cursor2.getString(cursor2.getColumnIndex("title"));
        String noteBodyText = cursor2.getString(cursor2.getColumnIndex("text"));

        db.close();

        Intent intent = new Intent(getContext(), activity_edit_note.class);

        intent.putExtra("note title", noteTitleText);
        intent.putExtra("note body text", noteBodyText);

        startActivity(intent);
    }
}
