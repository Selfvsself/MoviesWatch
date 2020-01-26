package com.selfvsself.movieswatch.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.selfvsself.movieswatch.AndroidApplication;
import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Presenter.IAddMoviePresenter;
import com.selfvsself.movieswatch.R;

import javax.inject.Inject;

public class AddMovieActivity extends AppCompatActivity {

    public static final String KEY_TITLE = "title";
    public static final String KEY_GENRE = "genre";
    public static final String KEY_DESC = "description";
    public static final String KEY_RATING = "rating";

    @Inject
    IAddMoviePresenter addMoviePresenter;

    AutoCompleteTextView inputGenre;

    SeekBar seekBar;
    private TextView textViewAssessment, textRating;
    ImageButton btnCancel;
    TextView btnSave;
    TextInputEditText inputTitle, inputDescription;
    TextInputLayout inputTitleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ((AndroidApplication) getApplication()).getAppComponent().inject(this);

        inputTitle = findViewById(R.id.inputTitle);
        inputTitleLayout = findViewById(R.id.textTitleInputLayout);
        inputDescription = findViewById(R.id.inputDescription);
        inputGenre = findViewById(R.id.inputGenre);
        inputGenre.setAdapter(new ArrayAdapter<>(this,
                R.layout.auto_complete_list_item, addMoviePresenter.getAllGenres()));

        seekBar = findViewById(R.id.seekBar);
        textViewAssessment = findViewById(R.id.textViewAssessment);
        textViewAssessment.setText(addMoviePresenter.getHintByRating(seekBar.getProgress()));
        textRating = findViewById(R.id.textRating);
        textRating.setText(Movie.formatRating(seekBar.getProgress()));
        btnSave = findViewById(R.id.buttonSave);
        btnCancel = findViewById(R.id.buttonCancel);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAssessment.setText(addMoviePresenter.getHintByRating(progress));
                textRating.setText(Movie.formatRating(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie addMovie = new Movie();
                addMovie.setTitle(inputTitle.getText().toString());
                addMovie.setGenre(inputGenre.getText().toString());
                addMovie.setDescription(inputDescription.getText().toString());
                addMovie.setRating(seekBar.getProgress());
                boolean isAdding = addMoviePresenter.addMovie(addMovie);
                if (isAdding) {
                    finish();
                } else {
                    inputTitleLayout.setError("Incorrect movie title");
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
    }
}
