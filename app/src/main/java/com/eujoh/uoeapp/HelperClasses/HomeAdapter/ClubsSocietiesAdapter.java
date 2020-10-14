package com.eujoh.uoeapp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eujoh.uoeapp.R;

import java.util.ArrayList;

public class ClubsSocietiesAdapter extends RecyclerView.Adapter<ClubsSocietiesAdapter.ClubsSocietiesViewHolder> {

    ArrayList<ClubsSocietiesHelperClass> clubsSocieties;

    public ClubsSocietiesAdapter(ArrayList<ClubsSocietiesHelperClass> clubsSocieties) {
        this.clubsSocieties = clubsSocieties;
    }

    @NonNull
    @Override
    public ClubsSocietiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clubs_societies_card_design,parent,false);
        ClubsSocietiesViewHolder clubsSocietiesViewHolder = new ClubsSocietiesViewHolder(view);
        return clubsSocietiesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClubsSocietiesViewHolder holder, int position) {
        ClubsSocietiesHelperClass clubsSocietiesHelperClass = clubsSocieties.get(position);
        holder.image.setImageResource(clubsSocietiesHelperClass.getImage());
        holder.title.setText(clubsSocietiesHelperClass.getTitle());
    }

    @Override
    public int getItemCount() {
        return clubsSocieties.size();
    }

    public static class ClubsSocietiesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        public ClubsSocietiesViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.clubs_societies_image);
            title = itemView.findViewById(R.id.clubs_societies_title_tv);
        }
    }
}
