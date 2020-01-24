package com.selfvsself.movieswatch.View;

import android.content.Intent;
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

public class EditMovieActivity extends AddMovieActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addMoviePresenter.setView(this);

        Intent intent = getIntent();

        final Movie movie = addMoviePresenter.getMovie(intent.getIntExtra("id", 0));

        inputTitle.setText(movie.getTitle());
        inputGenre.setText(movie.getGenre());
        inputDescription.setText(movie.getDescription());
        seekBar.setProgress(Integer.parseInt(movie.getRating()));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSaved = addMoviePresenter.editMovie(movie.getId());
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
}
