package com.example.architecturecomponentexample.ui.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.architecturecomponentexample.R;
import com.example.architecturecomponentexample.data.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shiraj Sayed
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes;

    public NoteAdapter() {
        notes = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.mTitle.setText(currentNote.getTitle());
        holder.mDescription.setText(currentNote.getDescription());
        holder.mPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView mTitle;
        private AppCompatTextView mDescription;
        private AppCompatTextView mPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.description);
            mPriority = itemView.findViewById(R.id.priority);
        }
    }
}
