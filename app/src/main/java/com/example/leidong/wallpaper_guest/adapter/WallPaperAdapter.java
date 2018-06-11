package com.example.leidong.wallpaper_guest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.leidong.wallpaper_guest.MainActivity;
import com.example.leidong.wallpaper_guest.R;
import com.example.leidong.wallpaper_guest.bean.WallPaperBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lei Dong on 2018/6/10.
 */
public class WallPaperAdapter extends RecyclerView.Adapter<WallPaperAdapter.ViewHolder> {
    private Context context;
    private List<WallPaperBean> wallPaperBeanList;

    public WallPaperAdapter(MainActivity mainActivity, List<WallPaperBean> wallPaperBeanList) {
        this.context = mainActivity;
        this.wallPaperBeanList = wallPaperBeanList;
    }

    @NonNull
    @Override
    public WallPaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.wallpaper_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WallPaperAdapter.ViewHolder holder, int position) {
        if(wallPaperBeanList != null){
            Picasso.get().load(wallPaperBeanList.get(position).getUrl()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        if(wallPaperBeanList != null) {
            return wallPaperBeanList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
