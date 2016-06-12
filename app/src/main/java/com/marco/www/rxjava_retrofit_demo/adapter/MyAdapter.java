package com.marco.www.rxjava_retrofit_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marco.www.rxjava_retrofit_demo.R;
import com.marco.www.rxjava_retrofit_demo.domain.Image;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/6/12.
 */
public class MyAdapter extends RecyclerView.Adapter
{
    private List<Image> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        Image image = images.get(position);
        System.out.println(image.image_url);
        Glide.with(holder.itemView.getContext()).load(image.image_url).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(image.description);
    }

    public void setImages(List<Image> images){
        this.images = images;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return images == null ? 0 : images.size();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;
        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
