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

public class RecentAnnouncementsAdapter extends RecyclerView.Adapter<RecentAnnouncementsAdapter.RecentAnnounceViewHolder> {

    ArrayList<RecentAnnouncementsHelperClass> recentAnnouncements;

    public RecentAnnouncementsAdapter(ArrayList<RecentAnnouncementsHelperClass> recentAnnouncements) {
        this.recentAnnouncements = recentAnnouncements;
    }

    @NonNull
    @Override
    public RecentAnnounceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_announcemets_card_design,parent,false);
        RecentAnnounceViewHolder recentAnnounceViewHolder = new RecentAnnounceViewHolder(view);
        return recentAnnounceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAnnounceViewHolder holder, int position) {
        RecentAnnouncementsHelperClass recentAnnouncementsHelperClass = recentAnnouncements.get(position);
        holder.image.setImageResource(recentAnnouncementsHelperClass.getImage());
        holder.title.setText(recentAnnouncementsHelperClass.getTitle());
        holder.desc.setText(recentAnnouncementsHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return recentAnnouncements.size();
    }

    public static class RecentAnnounceViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public RecentAnnounceViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.recent_announcements_image);
            title = itemView.findViewById(R.id.recent_announcements_title_tv);
            desc = itemView.findViewById(R.id.recent_announcements_desc_tv);
        }
    }
}
