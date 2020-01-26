package com.selfvsself.movieswatch.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.R;

public class EditMovieActivity extends AddMovieActivity {

    Movie oldMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        oldMovie = addMoviePresenter.getMovieByID(intent.getIntExtra("id", 0));

        TextView title = findViewById(R.id.titleOnAddActivity);
        title.setText(R.string.title_edit_movie);
        inputTitle.setText(oldMovie.getTitle());
        inputGenre.setText(oldMovie.getGenre());
        inputDescription.setText(oldMovie.getDescription());
        seekBar.setProgress(oldMovie.getRating());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie editableMovie = new Movie();
                editableMovie.setId(oldMovie.getId());
                editableMovie.setTitle(inputTitle.getText().toString());
                editableMovie.setGenre(inputGenre.getText().toString());
                editableMovie.setDescription(inputDescription.getText().toString());
                editableMovie.setRating(seekBar.getProgress());
                boolean isSaved = addMoviePresenter.editMovie(editableMovie);
                if (isSaved) {
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
