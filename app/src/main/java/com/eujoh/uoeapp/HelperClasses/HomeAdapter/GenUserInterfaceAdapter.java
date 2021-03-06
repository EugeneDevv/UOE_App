package com.eujoh.uoeapp.HelperClasses.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eujoh.uoeapp.R;
import com.eujoh.uoeapp.models.uploadsModel;

import java.util.ArrayList;

public class GenUserInterfaceAdapter extends RecyclerView.Adapter<GenUserInterfaceAdapter.ViewHolder>{
    private static final String Tag = "RecyclerView";

    private Context mContext;
    private ArrayList<uploadsModel> announcementsList;

    public GenUserInterfaceAdapter(Context mContext, ArrayList<uploadsModel> announcementsList) {
        this.mContext = mContext;
        this.announcementsList = announcementsList;
    }

    @NonNull
    @Override
    public GenUserInterfaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buzz_mostvied_care_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(announcementsList.get(position).getTitle());
        holder.description.setText(announcementsList.get(position).getDescription());

//        ImageView: Glide library
        Glide.with(mContext)
                .load(announcementsList.get(position).getImageURL())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return announcementsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //        widgets
        ImageView imageView;
        TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.most_vied_buzz_image);
            title = itemView.findViewById(R.id.most_vied_buzz_title_tv);
            description = itemView.findViewById(R.id.most_vied_buzz_desc_tv);
        }
    }
}

