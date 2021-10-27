package com.internashaala.task;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathRecyclerAdapter extends RecyclerView.Adapter<PathRecyclerAdapter.PathRecyclerViewHolder> {

    List<Path> pathList = new ArrayList<>();
    Context context;

    public PathRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<Path> titlesList)
    {
        this.pathList = titlesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PathRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img,parent,false);

        return new PathRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PathRecyclerViewHolder holder, int position) {

        Path item = pathList.get(position);
//        private void loadPermissions() {
//            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 107);
//            } else {
//
//                //loadVideo();
//            }
//        }


//        File imgFile = new  File(item.getPath());
//        Log.i("path",item.getPath());
//
//
//        Picasso.get().load(new File(item.getPath())).into(holder.galleryImgView);

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File file = new File(directory, item.getPath());
        holder.galleryImgView.setImageDrawable(Drawable.createFromPath(file.toString()));
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//            holder.galleryImgView.setImageBitmap(myBitmap);
//            Log.i("path",item.getPath());

//        if(imgFile.exists()){
//
//
//        }

    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    public class PathRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryImgView;
        public PathRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            galleryImgView = itemView.findViewById(R.id.image);

        }
    }
}

