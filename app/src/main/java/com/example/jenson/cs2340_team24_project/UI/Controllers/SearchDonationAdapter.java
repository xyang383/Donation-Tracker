package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jenson.cs2340_team24_project.R;

import java.util.ArrayList;

/**
 * The adapter to generate the layout for results of a donation search.
 */
public class SearchDonationAdapter extends RecyclerView.Adapter<SearchDonationAdapter.ViewHolder> {
    private final ArrayList<String> mLocationNames;
    private final Context mContext;

    public SearchDonationAdapter(Context context, ArrayList<String> mLocationNames) {
        this.mLocationNames = mLocationNames;
        this.mContext = context;
    }
    @Override
    public void onBindViewHolder(@NonNull SearchDonationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.locationName.setText(mLocationNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mLocationNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DetailDonationActivity.class);
                intent.putExtra("donation_name", mLocationNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @NonNull
    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public SearchDonationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        SearchDonationAdapter.ViewHolder holder = new SearchDonationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mLocationNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView locationName;
        final RelativeLayout parentLayout;
        ViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.locationname);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}