package com.example.trabalho2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    private ArrayList<Team> teams;

    public TeamsAdapter(ArrayList<Team> teams) {
        this.teams = teams;
    }



    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_recyclerview, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Team team = this.teams.get(position);

        holder.textTeamNameFill.setText(team.getName());
        holder.textTeamStarFill.setText(team.getStarOfTeam());
        holder.textTitlesFill.setText(team.getNumberTitles());
        holder.id.setText(team.getId().toString());
    }

    @Override
    public int getItemCount() {
        return this.teams.size();
    }

    class TeamsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutTripContainer;
        View viewBackground;
        TextView textTeamNameFill;
        TextView textTeamStarFill;
        TextView textTitlesFill;
        TextView id;

        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layoutTripContainer = itemView.findViewById(R.id.layoutContainer);
            this.viewBackground = itemView.findViewById(R.id.viewBackground);
            this.textTeamNameFill = itemView.findViewById(R.id.teamNameToFill);
            this.textTeamStarFill = itemView.findViewById(R.id.teamStarToFill);
            this.textTitlesFill = itemView.findViewById(R.id.titlesToFill);
            this.id = itemView.findViewById(R.id.teamId);
        }
    }


}
