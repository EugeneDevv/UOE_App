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

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.UpcomingEventsViewHolder>{

    ArrayList<UpcomingEventsHelperClass> upcomingEvents;

    public UpcomingEventsAdapter(ArrayList<UpcomingEventsHelperClass> upcomingEvents) {
        this.upcomingEvents = upcomingEvents;
    }

    @NonNull
    @Override
    public UpcomingEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_events_card_design,parent,false);
        UpcomingEventsViewHolder upcomingEventsViewHolder = new UpcomingEventsViewHolder(view);
        return upcomingEventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingEventsViewHolder holder, int position) {
        UpcomingEventsHelperClass upcomingEventsHelperClass = upcomingEvents.get(position);

        holder.image.setImageResource(upcomingEventsHelperClass.getImage());
        holder.title.setText(upcomingEventsHelperClass.getTitle());
        holder.desc.setText(upcomingEventsHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return upcomingEvents.size();
    }

    public static class UpcomingEventsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;
        public UpcomingEventsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.upcoming_events_image);
            title = itemView.findViewById(R.id.upcoming_events_title_tv);
            desc = itemView.findViewById(R.id.upcoming_events_desc_tv);
        }
    }
}
