package com.example.architecturecomponentexample;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architecturecomponentexample.data.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        NoteViewModel noteViewModel = ViewModelProviders.of(this)
                .get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this,
                new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        //Toast.makeText(MainActivity.this, "onChanged Called", Toast.LENGTH_LONG).show();
                        adapter.setNotes(notes);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
