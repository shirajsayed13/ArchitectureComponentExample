package com.example.architecturecomponentexample.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architecturecomponentexample.R;
import com.example.architecturecomponentexample.data.Note;
import com.example.architecturecomponentexample.ui.addnote.AddNoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Shiraj Sayed
 */
public class NotesListingActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.fab_button)
    FloatingActionButton mFabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_listing);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        mRecyclerView.setAdapter(adapter);

        NoteViewModel noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this,
                new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter.setNotes(notes);
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    @OnClick(R.id.fab_button)
    void onFabButtonClick() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}
