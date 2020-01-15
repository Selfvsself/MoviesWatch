package com.selfvsself.movieswatch.Model.RecyclerHelper;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.R;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null) {
            listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foregroundView = ((RecyclerAdapter.MyViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            View foregroundView = ((RecyclerAdapter.MyViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((RecyclerAdapter.MyViewHolder) viewHolder).viewForeground;
        View backgroundView = ((RecyclerAdapter.MyViewHolder) viewHolder).viewBacground;
        View editIcon = ((RecyclerAdapter.MyViewHolder) viewHolder).editIcon;
        View deleteIcon = ((RecyclerAdapter.MyViewHolder) viewHolder).deleteIcon;
        View editText = ((RecyclerAdapter.MyViewHolder) viewHolder).editText;
        View deleteText = ((RecyclerAdapter.MyViewHolder) viewHolder).deleteText;
        if (dX > 0) {
            backgroundView.setBackgroundResource(R.color.colorFloatButton3);
            editIcon.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.INVISIBLE);
            deleteIcon.setVisibility(View.VISIBLE);
            deleteText.setVisibility(View.VISIBLE);
        } else {
            backgroundView.setBackgroundResource(R.color.colorFloatButton2);
            editIcon.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
            deleteIcon.setVisibility(View.INVISIBLE);
            deleteText.setVisibility(View.INVISIBLE);
        }
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((RecyclerAdapter.MyViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
    }
}
