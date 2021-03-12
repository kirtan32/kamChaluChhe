package com.example.augmentedreality;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<Integer> imageObject;

    public RecycleViewAdapter(List<Integer> imageObject)
    {
        this.imageObject = imageObject;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycle, parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        int iconSrc = imageObject.get(position);
        holder.setData(iconSrc);
    }

    @Override
    public int getItemCount() {
        return imageObject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.placeHolder);

        }

        public void setData(int iconSrc) {
            imageView.setImageResource(iconSrc);
        }
    }
}
