package com.selfvsself.movieswatch.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
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
    private BottomSheetBehavior mbottomSheetBehavior;
    private LinearLayout layoutBottomSheet;
    private CustomTextView inputSearch;
    private int maxHeightRootLayout;
    private boolean isFocusSearchInput = false;
    float posY = 0;

    @Inject
    IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((AndroidApplication) getApplication()).getAppComponent().inject(this);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        mbottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        rootLayout = findViewById(R.id.root_layout);
        layoutBottomSheet = findViewById(R.id.layoutBottomSheet);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        inputSearch = findViewById(R.id.inputSearch);

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

        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackRight = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, new RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof RecyclerAdapter.MyViewHolder) {
                    final int editIndex = viewHolder.getAdapterPosition();
                    Intent intent = new Intent(getApplicationContext(), EditMovieActivity.class);
                    intent.putExtra("id", presenter.getIdMovie(editIndex));
                    startActivity(intent);
                    presenter.refreshItem(editIndex);
                }
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallbackLeft = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, new RecyclerItemTouchHelperListener() {
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

        presenter.sortByName();

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchItem(s.toString());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            posY = ev.getRawY();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (posY - ev.getRawY() > 270) floatingActionButton.hide();
            else if (posY - ev.getRawY() < -130) floatingActionButton.show();
            if (maxHeightRootLayout < rootLayout.getHeight()) {
                maxHeightRootLayout = rootLayout.getHeight();
            }
            if (ev.getRawY() < rootLayout.getHeight() - layoutBottomSheet.getHeight() && maxHeightRootLayout == rootLayout.getHeight() && mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case R.id.menuSearch:
                if (mbottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mbottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                return true;
            case R.id.menuByName:
                presenter.sortByName();
                return true;
            case R.id.menuByName2:
                presenter.sortByNameDown();
                return true;
            case R.id.menuByGenre:
                presenter.sortByGenre();
                return true;
            case R.id.menuByGenre2:
                presenter.sortGenreDown();
                return true;
            case R.id.menuByRating:
                presenter.sortByRating();
                return true;
            case R.id.menuByRating2:
                presenter.sortByRatingDown();
                return true;
//            case R.id.menuSetting:
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
