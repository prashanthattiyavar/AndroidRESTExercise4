package com.example.prashant.androidrestexercise.UserInterface.MVP;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prashant.androidrestexercise.Logic.DataObject;
import com.example.prashant.androidrestexercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemList extends RecyclerView.ViewHolder {


    @BindView(R.id.content_image)
    ImageView image;
    @BindView(R.id.content_descpt)
    TextView descrp;
    @BindView(R.id.content_name)
    TextView name;

    private Context context;

    public ItemList(View view, Context con) {
        super(view);
        this.context = con;
        ButterKnife.bind(this, view);
    }

    public void bind(DataObject data) {
        name.setText(data.getmName());
        descrp.setText(data.getmDescription());
        if (data.getmImage() != null) {
            Glide.with(context).load(data.getmImage()).into(image);
        } else {
            Glide.with(context).load(R.drawable.ic_launcher_background).into(image);
        }

    }
}
