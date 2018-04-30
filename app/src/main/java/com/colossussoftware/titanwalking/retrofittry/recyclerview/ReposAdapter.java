package com.colossussoftware.titanwalking.retrofittry.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colossussoftware.titanwalking.retrofittry.R;
import com.colossussoftware.titanwalking.retrofittry.model.Repo;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
    private List<Repo> repos;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvRepoName;
        public TextView tvRepoID;

        public ViewHolder(View view) {
            super(view);

            tvRepoName = (TextView) view.findViewById(R.id.tv_repoName);
            tvRepoID = (TextView) view.findViewById(R.id.tv_repoID);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReposAdapter(List<Repo> repos) {
        this.repos = repos;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_textview, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvRepoName.setText(repos.get(position).getName());
        holder.tvRepoID.setText(String.valueOf(repos.get(position).getId()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return repos.size();
    }
}
