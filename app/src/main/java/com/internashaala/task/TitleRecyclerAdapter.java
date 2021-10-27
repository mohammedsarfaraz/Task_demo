package com.internashaala.task;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TitleRecyclerAdapter extends RecyclerView.Adapter<TitleRecyclerAdapter.TitleRecyclerViewHolder> {

    List<User> titlesList = new ArrayList<>();
    Context context;
    TitleRecyclerInterface titleRecyclerInterface;

    public TitleRecyclerAdapter(Context context,TitleRecyclerInterface titleRecyclerInterface) {
        this.context = context;
        this.titleRecyclerInterface = titleRecyclerInterface;
    }

    public void updateList(List<User> titlesList)
    {
        this.titlesList = titlesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TitleRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new TitleRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleRecyclerViewHolder holder, int position) {

        User item = titlesList.get(position);

        holder.titleTv.setText(item.getTitle());

        holder.galleryImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleRecyclerInterface.onImageClick(item.getID());
            }
        });


       List<Path> pathList = MRoomDatabase.initialize(context).userDao().getAllPaths(item.getID());

       holder.adapter.updateList(pathList);

    }

    @Override
    public int getItemCount() {
        return titlesList.size();
    }

    public class TitleRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        ImageView galleryImgView;
        RecyclerView recyclerView;
        PathRecyclerAdapter adapter;

        public TitleRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.title);
            galleryImgView = itemView.findViewById(R.id.gallery);
            recyclerView = itemView.findViewById(R.id.recycler);
            setRecyclerView();
        }

        private void setRecyclerView() {
            adapter = new PathRecyclerAdapter(context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }
    }
}
