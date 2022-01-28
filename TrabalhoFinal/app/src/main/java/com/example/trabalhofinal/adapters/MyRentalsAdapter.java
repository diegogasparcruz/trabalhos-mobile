package com.example.trabalhofinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.activities.RentalDetailsActivity;
import com.example.trabalhofinal.models.Rent;

import java.util.ArrayList;

public class MyRentalsAdapter extends RecyclerView.Adapter<MyRentalsAdapter.MyRentalsVH> {
    private Context context;
    private ArrayList<Rent> rents;

    public MyRentalsAdapter(Context context) {
        this.context = context;
        this.rents = new ArrayList<>();
    }

    public void setRents(ArrayList<Rent> rents) {
        this.rents = rents;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyRentalsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_row, parent, false);
        return new MyRentalsAdapter.MyRentalsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRentalsVH holder, int position) {
        Rent rent = rents.get(position);

        holder.txtTitle.setText(rent.getTitle());
        holder.txtNumberBedroomsList.setText(rent.getNumberBedrooms()+"");
        holder.txtNumberBathroomsList.setText(rent.getNumberBathrooms()+"");
        holder.txtNumberResidentsList.setText(rent.getNumberResidents()+"");
        holder.txtMoney.setText("R$" + rent.getPrice());
        holder.txtMonth.setText("/mês");

        holder.cardDetails.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, RentalDetailsActivity.class);
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rents.size();
    }

    class MyRentalsVH extends RecyclerView.ViewHolder {
        TextView txtTitle, txtNumberBedroomsList, txtNumberBathroomsList, txtNumberResidentsList, txtMoney, txtMonth;
        CardView cardDetails;

        public MyRentalsVH(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtNumberBedroomsList = itemView.findViewById(R.id.txt_number_bedrooms_list);
            txtNumberBathroomsList = itemView.findViewById(R.id.txt_number_bathrooms_list);
            txtNumberResidentsList = itemView.findViewById(R.id.txt_number_residents_list);
            txtMoney = itemView.findViewById(R.id.txt_money);
            txtMonth = itemView.findViewById(R.id.txt_month);
            cardDetails = itemView.findViewById(R.id.card_details);
        }
    }
}
