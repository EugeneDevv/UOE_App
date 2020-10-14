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

public class CampusBuzzMvAdapter extends RecyclerView.Adapter<CampusBuzzMvAdapter.CampusBuzzMvViewHolder> {

    ArrayList<CampusBuzzMvHelperClass> mostViewedCampusBuzz;

    public CampusBuzzMvAdapter(ArrayList<CampusBuzzMvHelperClass> mostViewedCampusBuzz) {
        this.mostViewedCampusBuzz = mostViewedCampusBuzz;
    }

    @NonNull
    @Override
    public CampusBuzzMvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buzz_mostvied_care_design,parent,false);
        CampusBuzzMvViewHolder campusBuzzMvViewHolder = new CampusBuzzMvViewHolder(view);
        return campusBuzzMvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CampusBuzzMvViewHolder holder, int position) {
        CampusBuzzMvHelperClass campusBuzzMvHelperClass = mostViewedCampusBuzz.get(position);

        holder.image.setImageResource(campusBuzzMvHelperClass.getImage());
        holder.title.setText(campusBuzzMvHelperClass.getTitle());
        holder.desc.setText(campusBuzzMvHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostViewedCampusBuzz.size();
    }

    public static class CampusBuzzMvViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;
        public CampusBuzzMvViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.most_vied_buzz_image);
            title = itemView.findViewById(R.id.most_vied_buzz_title_tv);
            desc = itemView.findViewById(R.id.most_vied_buzz_desc_tv);
        }
    }
}
