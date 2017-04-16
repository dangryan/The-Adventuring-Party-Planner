package theadventuringparty.theadventuringpartyplanner;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Tab1Combat mTab1Combat = new Tab1Combat();
    Tab2Event mTab2Event = new Tab2Event();
    Tab3NPC mTab3NPC = new Tab3NPC();
    Tab4Notes mTab4Notes = new Tab4Notes();
    static private NoteDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("The Adventuring Party Planner");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_campaign) {
            Intent intent = new Intent(this,CampaignMotivation.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0: Tab1Combat tab1 = new Tab1Combat();
                    return tab1;
                case 1: Tab2Event tab2 = new Tab2Event();
                    return tab2;
                case 2: Tab3NPC tab3 = new Tab3NPC();
                    return tab3;
                case 3: Tab4Notes tab4 = new Tab4Notes();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "COMBAT";
                case 1:
                    return "EVENT";
                case 2:
                    return "NPC";
                case 3:
                    return "NOTES";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (mTab1Combat.isVisible() || mTab2Event.isVisible() || mTab3NPC.isVisible() || mTab4Notes.isVisible()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    public void onNewNoteButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), activity_new_note.class);
        startActivity(intent);
    }

    public void editNote(View view) {
        View parent = (View)view.getParent();
        TextView noteTextView = (TextView) parent.findViewById(R.id.noteTitleLabel);
        String noteTitle = noteTextView.getText().toString();
        mHelper = new NoteDbHelper(getApplicationContext());
        SQLiteDatabase db = mHelper.getWritableDatabase();

        String table = NoteContract.NoteEntry.TABLE;
        String[] columns = new String[] { "title", "text"};
        String selection = NoteContract.NoteEntry.COL_NOTE_TITLE+ "= '" + noteTitle + "'";


        Cursor cursor2 = db.query(table, columns, selection,null,null,null,null,null);

        cursor2.moveToFirst();

        String noteTitleText = cursor2.getString(cursor2.getColumnIndex("title"));
        String noteBodyText = cursor2.getString(cursor2.getColumnIndex("text"));

        db.close();

        Intent intent = new Intent(getApplicationContext(), activity_edit_note.class);

        intent.putExtra("note title", noteTitleText);
        intent.putExtra("note body text", noteBodyText);

        startActivity(intent);
    }

    private void updateNoteTitle() {
        //section to update the Title
        ArrayList<String> noteNameList = new ArrayList<>();
        ListView mNoteListView = (ListView)findViewById(R.id.noteListView);

        mHelper = new NoteDbHelper(getApplicationContext());
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
            mAdapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.note_display_layout,
                    //adds data to TextView
                    R.id.noteTitleLabel,
                    noteNameList);
            mNoteListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(noteNameList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    /*public void deleteNote(View view) {
        View parent = (View) view.getParent();
        TextView noteTextView = (TextView)parent.findViewById(R.id.noteTitleLabel);
        String noteTitle = noteTextView.getText().toString();

        mHelper = new NoteDbHelper(getApplicationContext());
        SQLiteDatabase db = mHelper.getWritableDatabase();

        db.delete(NoteContract.NoteEntry.TABLE,"title = '" + noteTitle + "'",null);

        db.close();
        updateNoteTitle();
    }*/
}