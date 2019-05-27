package com.example.architecturecomponentexample.ui.addnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.architecturecomponentexample.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Shiraj Sayed
 */
public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "com.example.architecturecomponentexample.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.example.architecturecomponentexample.EXTRA_DESCRIPTION";

    public static final String EXTRA_PRIORITY =
            "com.example.architecturecomponentexample.EXTRA_PRIORITY";

    @BindView(R.id.description_edit_text)
    AppCompatEditText mDescription;

    @BindView(R.id.title_edit_text)
    AppCompatEditText mTitle;

    @BindView(R.id.number_picker)
    NumberPicker mNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(10);

        Objects.requireNonNull(getSupportActionBar())
                .setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = Objects.requireNonNull(mTitle.getText()).toString();
        String description = Objects.requireNonNull(mDescription.getText()).toString();
        int priority = mNumberPicker.getValue();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_PRIORITY, priority);

        setResult(RESULT_OK, intent);
        finish();
    }
}
