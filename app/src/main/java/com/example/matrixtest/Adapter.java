package com.example.matrixtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Item> mData;
    Context context;
    int col;
    MainActivity mainActivity;
    int dem=0;
    public Adapter(List<Item> mData, Context context,int col, MainActivity mainActivity) {
        this.mData = mData;
        this.context = context;
        this.col = col;
        this.mainActivity=mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matrix, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.linearLayout.setMinimumHeight(9*40/col);
        holder.linearLayout.setMinimumWidth(10*40/col);
        for (int i=0;i<(col*col)/2;i++){
            if (position==GenerateRandom(mData.size())) {
                holder.linearLayout.setBackgroundResource(R.color.blue);
                mData.get(position).setColor(R.color.blue);
            }
        }
        for (int i=0;i<(col*col)/2;i++){
            if (position==GenerateRandom(mData.size())) {
                if (mData.get(position).getColor() != R.color.blue) {
                    holder.linearLayout.setBackgroundResource(R.color.red);
                    mData.get(position).setColor(R.color.red);
                    mainActivity.Gmain[dem]=position;
                    dem++;
                    mainActivity.dem=dem;
                    Log.v("AAA",position/col+"/"+position%col);
                    Log.v("H",""+mainActivity.Gmain[dem]);
                    Log.v("DEM",""+mainActivity.dem);
//

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           linearLayout= itemView.findViewById(R.id.linearBackrgound);
           textView=itemView.findViewById(R.id.info_text);
        }
    }
    private int GenerateRandom(int n) {
            int min = 1;
            int max = n;
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            return random_int;
    }
}
