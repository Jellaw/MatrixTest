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

import java.util.List;

public class GoAdapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Item> mData;
    Context context;
    int dem;
    int G[];
    int col;

    public GoAdapter(List<Item> mData, Context context, int dem, int G[],int col) {
        this.mData = mData;
        this.context = context;
        this.dem = dem;
        this.G = G;
        this.col=col;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matrix, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.linearLayout.setMinimumHeight(9*40/col);
        holder.linearLayout.setMinimumWidth(10*40/col);
        test(position);
        Log.v("pos",""+position);
        for (int i = 0; i<dem-1;i++){
            if (position==G[i]&&test(position)!=0){
                holder.linearLayout.setBackgroundResource(R.color.red);
                holder.textView.setText("x");
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
            linearLayout = itemView.findViewById(R.id.linearBackrgound);
            textView = itemView.findViewById(R.id.info_text);
        }
    }
    private int test(int n) {
        int k = 0;
        int testPosition = 0;
        for (int i = 0; i < dem; i++) {
            if (n + 1 == G[i]&&(n+1)%8!=0) {
                k++;
            }
        }
        for (int i = 0; i < dem; i++) {
            if (n - 1 == G[i]&&n%col!=0) {
                k++;
            }
        }
        for (int i = 0; i < dem; i++) {
            if (n + col == G[i]) {
                k++;
            }
        }
        for (int i = 0; i < dem; i++) {
            if (n - col == G[i]) {
                k++;
            }
        }
        if (k != 0) testPosition = 1;
        return testPosition;
    }
}