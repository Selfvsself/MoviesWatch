package com.selfvsself.movieswatch.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.selfvsself.movieswatch.R;

public class AddMovieActivity extends AppCompatActivity {
    AutoCompleteTextView mAutoCompleteTextView;
    final String[] mCats = { "Action", "Anime", "Adventure", "Animation", "Biography",
            "Comedy", "Documentary", "Drama", "Family", "Fantasy", "History", "Horror",
            "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Short Film",
            "Sport", "Superhero", "Thriller", "War", "Western" , "Аниме" , "Биография",
            "Биография", "Боевик", "Вестерн", "Военный", "Детектив",
            "Детский", "Для взрослых", "Документальный", "Драма", "Игра",
            "История", "Комедия", "Концерт", "Короткометражка", "Криминал", "Мелодрама",
            "Музыка", "Мультфильм", "Мюзикл", "Приключения", "Семейный", "Сериал", "Спорт",
            "Триллер", "Ужасы", "Фантастика", "Фильм нуар", "Фэнтези"};
    private SeekBar seekBar;
    private TextView textView;
    private ImageButton btnCancel;
    private TextView btnSave;
    private final String SEEK_VALUE_0 = "I'll watch a movie when it’s boring";
    private final String SEEK_VALUE_1 = "Сan be seen this month";
    private final String SEEK_VALUE_2 = "I can watch a look on the phone on the road";
    private final String SEEK_VALUE_3 = "I'll look at the weekend";
    private final String SEEK_VALUE_4 = "Urgent need to see";
    private final String SEEK_VALUE_5 = "What I have been waiting for my whole life";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        mAutoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                R.layout.auto_complete_list_item, mCats));

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView333);
        btnSave = findViewById(R.id.buttonSave);
        btnCancel = findViewById(R.id.buttonCancel);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 1) {
                    textView.setText(SEEK_VALUE_0);
                } else if (progress < 2) {
                    textView.setText(SEEK_VALUE_1);
                } else if (progress < 3) {
                    textView.setText(SEEK_VALUE_2);
                } else if (progress < 4) {
                    textView.setText(SEEK_VALUE_3);
                } else if (progress < 5) {
                    textView.setText(SEEK_VALUE_4);
                } else {
                    textView.setText(SEEK_VALUE_5);
                }
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
}
