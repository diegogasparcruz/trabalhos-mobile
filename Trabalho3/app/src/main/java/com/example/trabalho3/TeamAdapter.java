package com.example.trabalho3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Team> list = new ArrayList<>();

    public TeamAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<Team> teams) {
        this.list.addAll(teams);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_team, parent, false);
        return new TeamVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Team t = null;
        this.onBindViewHolder(holder, position, t);
    }
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Team t) {
        TeamVH vh = (TeamVH) holder;
        Team team = t == null ? this.list.get(position) : t;

        vh.txt_team_name.setText(team.getName());
        vh.txt_team_star.setText(team.getStar());
        vh.txt_team_titles.setText(String.valueOf(team.getTitles()));

        vh.txt_option.setOnClickListener(v ->
        {
            PopupMenu popupMenu = new PopupMenu(context, vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item ->
            {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context, Form.class);
                        intent.putExtra("EDIT", team);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOTeam dao = new DAOTeam();
                        dao.remove(team.getKey()).addOnSuccessListener(suc ->
                        {
                            Toast.makeText(context, "Time removido da lista", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            this.list.remove(team);
                        }).addOnFailureListener(er ->
                        {
                            Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class TeamVH extends RecyclerView.ViewHolder {
        public TextView txt_team_name, txt_team_star, txt_team_titles, txt_option;

        public TeamVH(@NonNull View itemView) {
            super(itemView);
            txt_team_name = itemView.findViewById(R.id.txt_team_name);
            txt_team_star = itemView.findViewById(R.id.txt_team_star);
            txt_team_titles = itemView.findViewById(R.id.txt_team_titles);
            txt_option = itemView.findViewById(R.id.txt_option);
        }
    }
}
