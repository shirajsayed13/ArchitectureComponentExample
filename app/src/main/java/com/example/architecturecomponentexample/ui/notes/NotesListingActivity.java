package com.example.architecturecomponentexample.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architecturecomponentexample.R;
import com.example.architecturecomponentexample.data.Note;
import com.example.architecturecomponentexample.ui.addnote.AddNoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Shiraj Sayed
 */
public class NotesListingActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST_CODE = 1;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.fab_button)
    FloatingActionButton mFabButton;

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_listing);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        mRecyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this,
                notes -> {
                    adapter.setNotes(notes);
                    mProgressBar.setVisibility(View.GONE);
                });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(NotesListingActivity.this, "Note Deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @OnClick(R.id.fab_button)
    void onFabButtonClick() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
                String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
                int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

                Note note = new Note(title, description, priority);
                noteViewModel.insert(note);

                Toast.makeText(this, "Note is Saved", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Note Not Saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all_notes) {
            noteViewModel.deleteAllNotes();
            Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
