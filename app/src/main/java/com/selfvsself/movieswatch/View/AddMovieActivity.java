package com.selfvsself.movieswatch.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.selfvsself.movieswatch.R;

public class AddMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
    }
}
