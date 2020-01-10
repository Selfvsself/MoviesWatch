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
import com.selfvsself.movieswatch.AndroidApplication;
import com.selfvsself.movieswatch.Presenter.IAddMoviePresenter;
import com.selfvsself.movieswatch.R;

import javax.inject.Inject;

public class AddMovieActivity extends AppCompatActivity implements AddMovieActivityView{

    @Inject
    IAddMoviePresenter addMoviePresenter;

    AutoCompleteTextView inputGenre;

    private SeekBar seekBar;
    private TextView textViewAssessment;
    private ImageButton btnCancel;
    private TextView btnSave;
    private TextInputEditText inputTitle, inputDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ((AndroidApplication) getApplication()).getAppComponent().inject(this);
        addMoviePresenter.setView(this);

        inputTitle = findViewById(R.id.inputTitle);
        inputDescription = findViewById(R.id.inputDescription);
        inputGenre = findViewById(R.id.inputGenre);
        inputGenre.setAdapter(new ArrayAdapter<>(this,
                R.layout.auto_complete_list_item, addMoviePresenter.getAllGenres()));

        seekBar = findViewById(R.id.seekBar);
        textViewAssessment = findViewById(R.id.textViewAssessment);
        btnSave = findViewById(R.id.buttonSave);
        btnCancel = findViewById(R.id.buttonCancel);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addMoviePresenter.setProgressSeekBar(progress);
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
                finish();
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
}
