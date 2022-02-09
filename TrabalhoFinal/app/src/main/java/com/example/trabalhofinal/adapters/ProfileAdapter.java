package com.example.trabalhofinal.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.activities.BookMarksActivity;
import com.example.trabalhofinal.activities.LoginActivity;
import com.example.trabalhofinal.activities.MainActivity;
import com.example.trabalhofinal.activities.MyRentalsActivity;
import com.example.trabalhofinal.activities.ProfileFormActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileVH> {
    private FirebaseAuth mAuth;
    private final ArrayList<Integer> icons;
    private final ArrayList<String> userDataTitles;
    private final ArrayList<String> userDataContents;
    private final Context context;


    public ProfileAdapter(Context context) {
        this.context = context;

        mAuth = FirebaseAuth.getInstance();

        icons = new ArrayList<Integer>();
        icons.add(R.drawable.ic_person);
        icons.add(R.drawable.ic_apartment);
        icons.add(R.drawable.ic_bookmark);
        icons.add(R.drawable.ic_logout);

        userDataTitles = new ArrayList<String>();
        userDataTitles.add("Conta");
        userDataTitles.add("Anúncios");
        userDataTitles.add("Itens salvos");
        userDataTitles.add("Sair");

        userDataContents = new ArrayList<String>();
        userDataContents.add("Meus dados");
        userDataContents.add("Meus anúncios");
        userDataContents.add("Meus itens salvos");
        userDataContents.add("Logout");
    }


    @Override
    public void onBindViewHolder(@NonNull ProfileVH holder, @SuppressLint("RecyclerView") int position) {
        holder.iconImage.setImageResource(icons.get(position));
        holder.userDataTitle.setText(userDataTitles.get(position));
        holder.userDataContent.setText(userDataContents.get(position));

        holder.nextAction.setImageResource(R.drawable.ic_next);

        if (position == 3) { //POSIÇÃO DO LOGOUT
            holder.userDataTitle.setTextColor(context.getResources().getColor(R.color.red));
            holder.userDataContent.setTextColor(context.getResources().getColor(R.color.red));
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0: { // MEUS DADOS
                        Intent intent = new Intent(context, ProfileFormActivity.class);
                        context.startActivity(intent);
                        break;
                    }
                    case 1: { // MEUS ANÚNCIOS
                        Intent intent = new Intent(context, MyRentalsActivity.class);
                        context.startActivity(intent);
                        break;
                    }
                    case 2: { // ITENS SALVOS
                        Intent intent = new Intent(context, BookMarksActivity.class);
                        context.startActivity(intent);
                        break;
                    }
                    case 3: {
                        new AlertDialog.Builder(context)
                                .setMessage("Deseja fazer logout?")
                                .setCancelable(false)
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mAuth.signOut();

                                        Intent intent = new Intent(context, LoginActivity.class);
                                        context.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Não", null)
                                .show();
                        break;
                    }
                    default: {

                        break;
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public ProfileVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_item_profile_row, parent, false);
        return new ProfileVH(view);
    }

    @Override
    public int getItemCount() {
        return userDataContents.size();
    }

    public static class ProfileVH extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView userDataTitle;
        TextView userDataContent;
        ImageView nextAction;
        RelativeLayout parentLayout;

        public ProfileVH(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            userDataTitle = itemView.findViewById(R.id.data_title);
            userDataContent = itemView.findViewById(R.id.data_content);
            iconImage = itemView.findViewById(R.id.icon_start);
            nextAction = itemView.findViewById(R.id.icon_end);
        }
    }
}
