package com.selfvsself.movieswatch.Model.RecyclerHelper;

import androidx.recyclerview.widget.RecyclerView;

public interface RecyclerItemTouchHelperListener {

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
