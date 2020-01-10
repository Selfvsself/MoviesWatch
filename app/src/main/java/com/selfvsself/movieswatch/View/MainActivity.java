package com.selfvsself.movieswatch.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.selfvsself.movieswatch.AndroidApplication;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.Presenter.IMainPresenter;
import com.selfvsself.movieswatch.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    private FloatingActionButton floatingActionButton;

    @Inject
    IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((AndroidApplication) getApplication()).getAppComponent().inject(this);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = presenter.getRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }
}
