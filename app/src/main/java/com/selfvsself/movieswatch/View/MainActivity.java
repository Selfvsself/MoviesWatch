package com.selfvsself.movieswatch.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.selfvsself.movieswatch.AndroidApplication;
import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.Model.RecyclerHelper.RecyclerItemTouchHelper;
import com.selfvsself.movieswatch.Model.RecyclerHelper.RecyclerItemTouchHelperListener;
import com.selfvsself.movieswatch.Presenter.IMainPresenter;
import com.selfvsself.movieswatch.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout rootLayout;

    @Inject
    IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((AndroidApplication) getApplication()).getAppComponent().inject(this);

        rootLayout = findViewById(R.id.root_layout);
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
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, new RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
                    final int deletedIndex = viewHolder.getAdapterPosition();

                    final Movie deletedMovie = presenter.deleteMovie(deletedIndex);

                    Snackbar snackbar = Snackbar.make(rootLayout, deletedMovie.getTitle() + " removed", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.addMovie(deletedMovie);
                        }
                    });
                    snackbar.show();
                }
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, new RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
                    final int deletedIndex = viewHolder.getAdapterPosition();

                    final Movie deletedMovie = presenter.deleteMovie(deletedIndex);

                    Snackbar snackbar = Snackbar.make(rootLayout, deletedMovie.getTitle() + " removed", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.addMovie(deletedMovie);
                        }
                    });
                    snackbar.show();
                }

            }
        });
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(recyclerView);
    }
}
