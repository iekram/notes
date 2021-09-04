package com.bug.corvus.notes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bug.corvus.notes.R;
import com.bug.corvus.notes.database.NotesDatabase;
import com.bug.corvus.notes.entities.Note;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteSubtitle, inputNoteText;
    private TextView textDateTime;
    private View viewSubtitleIndicator;

    private String selectedNoteColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> onBackPressed());

        inputNoteTitle = findViewById(R.id.input_note_title);
        inputNoteSubtitle = findViewById(R.id.input_note_subtitle);
        inputNoteText = findViewById(R.id.input_note);
        textDateTime = findViewById(R.id.text_date_time);
        viewSubtitleIndicator = findViewById(R.id.view_subtitle_indicator);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );
        ImageView imageSave = findViewById(R.id.image_save);
        imageSave.setOnClickListener(v -> saveNote());

        selectedNoteColor = "#333333";

        initMiscellaneous();
        setSubtitleIndicatorColor();
    }

    private void saveNote() {
        if (inputNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note title is required", Toast.LENGTH_SHORT).show();
            return;
        } else if (inputNoteSubtitle.getText().toString().trim().isEmpty() && inputNoteSubtitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        final Note note = new Note();
        note.setTitle(inputNoteTitle.getText().toString());
        note.setSubtitle(inputNoteSubtitle.getText().toString());
        note.setNoteText(inputNoteText.getText().toString());
        note.setDateTime(textDateTime.getText().toString());
        note.setColor(selectedNoteColor);

        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).noteDao().insert(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        new SaveNoteTask().execute();
    }

    private void initMiscellaneous() {
        final LinearLayout layoutMiscellaneous = findViewById(R.id.layout_miscellaneous);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous);
        layoutMiscellaneous.findViewById(R.id.text_miscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final ImageView imageView1 = layoutMiscellaneous.findViewById(R.id.image_color_1);
        final ImageView imageView2 = layoutMiscellaneous.findViewById(R.id.image_color_2);
        final ImageView imageView3 = layoutMiscellaneous.findViewById(R.id.image_color_3);
        final ImageView imageView4 = layoutMiscellaneous.findViewById(R.id.image_color_4);
        final ImageView imageView5 = layoutMiscellaneous.findViewById(R.id.image_color_5);

        layoutMiscellaneous.findViewById(R.id.view_color_1).setOnClickListener(v -> {
            selectedNoteColor = "#333333";
            imageView1.setImageResource(R.drawable.ic_baseline_done_24);
            imageView2.setImageResource(0);
            imageView3.setImageResource(0);
            imageView4.setImageResource(0);
            imageView5.setImageResource(0);
            setSubtitleIndicatorColor();
        });

        layoutMiscellaneous.findViewById(R.id.view_color_2).setOnClickListener(v -> {
            selectedNoteColor = "#FDBE3B";
            imageView1.setImageResource(0);
            imageView2.setImageResource(R.drawable.ic_baseline_done_24);
            imageView3.setImageResource(0);
            imageView4.setImageResource(0);
            imageView5.setImageResource(0);
            setSubtitleIndicatorColor();
        });

        layoutMiscellaneous.findViewById(R.id.view_color_3).setOnClickListener(v -> {
            selectedNoteColor = "#FF4842";
            imageView1.setImageResource(0);
            imageView2.setImageResource(0);
            imageView3.setImageResource(R.drawable.ic_baseline_done_24);
            imageView4.setImageResource(0);
            imageView5.setImageResource(0);
            setSubtitleIndicatorColor();
        });

        layoutMiscellaneous.findViewById(R.id.view_color_4).setOnClickListener(v -> {
            selectedNoteColor = "#3A52FC";
            imageView1.setImageResource(0);
            imageView2.setImageResource(0);
            imageView3.setImageResource(0);
            imageView4.setImageResource(R.drawable.ic_baseline_done_24);
            imageView5.setImageResource(0);
            setSubtitleIndicatorColor();
        });

        layoutMiscellaneous.findViewById(R.id.view_color_5).setOnClickListener(v -> {
            selectedNoteColor = "#000000";
            imageView1.setImageResource(0);
            imageView2.setImageResource(0);
            imageView3.setImageResource(0);
            imageView4.setImageResource(0);
            imageView5.setImageResource(R.drawable.ic_baseline_done_24);
            setSubtitleIndicatorColor();
        });
    }

    private void setSubtitleIndicatorColor() {
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNoteColor));
    }
}