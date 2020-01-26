package com.selfvsself.movieswatch.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selfvsself.movieswatch.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Movie> movieList;

    public RecyclerAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textTitle.setText(movieList.get(position).getTitle());
        holder.textGenre.setText(movieList.get(position).getGenre());
        holder.textRating.setText(movieList.get(position).getFormattedRating());
        holder.textDescription.setText(movieList.get(position).getDescription());
//        holder.debugIdMovieText.setText(String.valueOf(movieList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView textTitle, textGenre, textDescription, textRating;
        //        final TextView debugIdMovieText;
        public RelativeLayout viewForeground, viewBacground;
        public TextView editText, deleteText;
        public ImageView editIcon, deleteIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.item_title);
            textGenre = itemView.findViewById(R.id.item_genre);
            textDescription = itemView.findViewById(R.id.item_description);
            textRating = itemView.findViewById(R.id.item_rating);
            viewForeground = itemView.findViewById(R.id.itemCardView);
            viewBacground = itemView.findViewById(R.id.background);
            editIcon = itemView.findViewById(R.id.editIcon);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            editText = itemView.findViewById(R.id.editText);
            deleteText = itemView.findViewById(R.id.deleteText);
//            debugIdMovieText = itemView.findViewById(R.id.idMovieDebugText);
        }
    }
}
