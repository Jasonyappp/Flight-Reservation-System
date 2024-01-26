package com.example.finalasignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private final List<ReviewData> reviewList;
    private final Context context;

    public ReviewAdapter(Context context, List<ReviewData> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView usernameTextView;
        public final RatingBar ratingBar;
        public final TextView reviewCommentTextView;

        public ViewHolder(View view) {
            super(view);
            usernameTextView = view.findViewById(R.id.textViewUsername);
            ratingBar = view.findViewById(R.id.ratingBar);
            reviewCommentTextView = view.findViewById(R.id.textViewReviewComment);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewData review = reviewList.get(position);

        // Set review information to the ViewHolder
        holder.ratingBar.setRating(review.getRating());
        holder.reviewCommentTextView.setText("Review Comment: " + review.getReviewText());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}
