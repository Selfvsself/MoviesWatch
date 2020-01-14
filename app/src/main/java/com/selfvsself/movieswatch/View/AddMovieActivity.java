package com.selfvsself.movieswatch.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.selfvsself.movieswatch.Presenter.IAddMoviePresenter;
import com.selfvsself.movieswatch.R;

import javax.inject.Inject;

public class AddMovieActivity extends AppCompatActivity implements AddMovieActivityView{

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
        addMoviePresenter.setView(this);

        inputTitle = findViewById(R.id.inputTitle);
        inputTitleLayout = findViewById(R.id.textTitleInputLayout);
        inputDescription = findViewById(R.id.inputDescription);
        inputGenre = findViewById(R.id.inputGenre);
        inputGenre.setAdapter(new ArrayAdapter<>(this,
                R.layout.auto_complete_list_item, addMoviePresenter.getAllGenres()));

        seekBar = findViewById(R.id.seekBar);
        textViewAssessment = findViewById(R.id.textViewAssessment);
        textRating = findViewById(R.id.textRating);
        btnSave = findViewById(R.id.buttonSave);
        btnCancel = findViewById(R.id.buttonCancel);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addMoviePresenter.setProgressSeekBar(progress);
                String str = String.valueOf(progress);
                float formattedRating= 2.5f + Float.parseFloat(str) / 2;
                textRating.setText(String.valueOf(formattedRating));
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
                boolean isSaved = addMoviePresenter.saveMovie();
                if (isSaved) {
                    finish();
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
    }

    @Override
    public void setAssessmentText(String message) {
        textViewAssessment.setText(message);
    }

    @Override
    public String getMovieTitle() {
        return inputTitle.getText().toString();
    }

    @Override
    public String getMovieGenre() {
        return inputGenre.getText().toString();
    }

    @Override
    public String getMovieDescription() {
        return inputDescription.getText().toString();
    }

    @Override
    public String getMovieRating() {
        return String.valueOf(seekBar.getProgress());
    }

    @Override
    public void setErrorMsgInTitle(String errorMsg) {
        inputTitleLayout.setError(errorMsg);
    }
}
