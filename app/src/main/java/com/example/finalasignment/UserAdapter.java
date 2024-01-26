package com.example.finalasignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final List<User> userList;
    private final Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        // Set user information to the ViewHolder
        holder.usernameTextView.setText("Username: " + user.getUsername());
        holder.icNumberTextView.setText("IC Number: " + user.getICNumber());
        holder.dobTextView.setText("Date of Birth: " + user.getDOB());
        holder.emailTextView.setText("Email Address: " + user.getEmail());
        holder.phoneNumberTextView.setText("Phone Number: " + user.getFormattedPhoneNumber());
        holder.passwordTextView.setText("Password: " + user.getPassword());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView usernameTextView;
        public final TextView icNumberTextView;
        public final TextView dobTextView;
        public final TextView emailTextView;
        public final TextView phoneNumberTextView;
        public final TextView passwordTextView;

        public ViewHolder(View view) {
            super(view);
            usernameTextView = view.findViewById(R.id.textViewUsername);
            icNumberTextView = view.findViewById(R.id.textViewICNumber);
            dobTextView = view.findViewById(R.id.textViewDOB);
            emailTextView = view.findViewById(R.id.textViewEmail);
            phoneNumberTextView = view.findViewById(R.id.textViewPhoneNumber);
            passwordTextView = view.findViewById(R.id.textViewPassword);
        }
    }
}
