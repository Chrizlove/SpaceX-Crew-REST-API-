package com.chrizlove.spacexcrewapi;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {

        List<CrewModel> crewList;

        public CrewAdapter(List<CrewModel> crewList) {
            this.crewList = crewList;
        }


    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        holder.setData(crewList.get(position));
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_item_lyt,parent,false);
        return new CrewViewHolder(view);
    }

    @Override
        public int getItemCount() {
            return crewList.size();
        }

        class CrewViewHolder extends RecyclerView.ViewHolder {

            TextView crewName, crewAgency, crewWiki, crewStatus;
            ImageView crewImage;

            public CrewViewHolder(@NonNull View itemView) {
                super(itemView);

                crewImage = itemView.findViewById(R.id.crew_image);
                crewName = itemView.findViewById(R.id.crew_name);
                crewAgency = itemView.findViewById(R.id.crew_agency);
                crewWiki = itemView.findViewById(R.id.crew_wikipedia);
                crewStatus = itemView.findViewById(R.id.crew_status);
            }
            void setData(CrewModel crewModel)
            {
                crewName.setText(crewModel.getName());
                crewAgency.setText(crewModel.getAgency());
                crewWiki.setText(crewModel.getWikipedia());
                crewStatus.setText(crewModel.getStatus());
                Glide.with(crewImage.getContext()).load(Uri.parse(crewModel.getImage())).into(crewImage);
            }
        }

        public void updateCrew(List<CrewModel> newCrewList)
        {
            crewList.clear();
            crewList.addAll(newCrewList);
            notifyDataSetChanged();
        }

    }

