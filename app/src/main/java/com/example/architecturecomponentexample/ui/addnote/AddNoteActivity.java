package com.example.architecturecomponentexample.ui.addnote;

import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.architecturecomponentexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Shiraj Sayed
 */
public class AddNoteActivity extends AppCompatActivity {

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
    }
}
