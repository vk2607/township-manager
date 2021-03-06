package com.township.manager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AmenitiesAdapter extends RecyclerView.Adapter {

    ArrayList<Amenity> dataset;
    Context context;
    String type;

    public AmenitiesAdapter(ArrayList<Amenity> dataset, Context context, String type) {
        this.dataset = dataset;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_amenities, parent, false);
        AmenitiesAdapter.ViewHolder viewHolder = new AmenitiesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Amenity amenity = dataset.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.name.setText(amenity.getName());
        viewHolder.oneTimeCost.setText("₹ " + amenity.getBilling_rate());
        viewHolder.timing.setText("8AM to 10PM");
        if (amenity.getFree_for_members()) {
            viewHolder.freeForMembers.setText("Free for members");
        } else {
            viewHolder.freeForMembers.setText("Not free for members");
        }
        if (type.equals("admin")) {
            viewHolder.reserveSlot.setVisibility(View.GONE);
        } else {
            viewHolder.reserveSlot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AmenitySlotsActivity.class);
                    intent.putExtra("amenity_id", amenity.getAmenity_id());
                    intent.putExtra("free_for_members", amenity.getFree_for_members());
                    intent.putExtra("amount", amenity.getBilling_rate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, oneTimeCost, freeForMembers, timing;
        MaterialButton reserveSlot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recycler_amenity_name_text_view);
            oneTimeCost = itemView.findViewById(R.id.recycler_amenity_one_time_cost_text_view);
            freeForMembers = itemView.findViewById(R.id.recycler_amenity_membership_details_text_view);
            timing = itemView.findViewById(R.id.recycler_amenity_timing_text_view);
            reserveSlot = itemView.findViewById(R.id.recycler_amenity_reserve_slot_button);
        }
    }

}
