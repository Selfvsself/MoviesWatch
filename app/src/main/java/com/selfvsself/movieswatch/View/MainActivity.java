package com.selfvsself.movieswatch.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout rootLayout;
    private static final String WEB_LINK = "https://github.com/Selfvsself/MoviesWatch";

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
        recyclerView.hasFixedSize();
        RecyclerAdapter adapter = presenter.getRecyclerAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, new RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
                    final int editIndex = viewHolder.getAdapterPosition();
                    Intent intent = new Intent(getApplicationContext(), EditMovieActivity.class);
                    intent.putExtra("id", presenter.getMovieID(editIndex));
                    startActivity(intent);
                    presenter.refreshViewOnItem(editIndex);
                }
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, new RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
                    final int deletedIndex = viewHolder.getAdapterPosition();
                    final Movie deletedMovie = presenter.deleteMovie(deletedIndex);

                    Snackbar snackbar = Snackbar.make(rootLayout, deletedMovie.getTitle() + " removed", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.restoringMovie(deletedMovie);
                        }
                    });
                    snackbar.show();
                }

            }
        });
        new ItemTouchHelper(itemTouchHelperCallbackRight).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(itemTouchHelperCallbackLeft).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSetting:
                View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_about, null);
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                TextView btnOk = dialog.findViewById(R.id.btn_dialog_ok);
                btnOk.setOnClickListener(v -> alertDialog.cancel());
                TextView textLink = dialog.findViewById(R.id.about_text_link);
                textLink.setOnClickListener(v -> {
                    Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_LINK));
                    startActivity(openLinkIntent);
                });
                alertDialog.setView(dialog);
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}
