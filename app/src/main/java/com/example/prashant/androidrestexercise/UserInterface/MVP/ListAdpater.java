package com.example.prashant.androidrestexercise.UserInterface.MVP;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prashant.androidrestexercise.Logic.DataObject;
import com.example.prashant.androidrestexercise.R;

import java.util.List;

public class ListAdpater extends RecyclerView.Adapter<ItemList> {

    private List<DataObject> dataList;
    private ListContractor.Presenter presenter;
    private Context context;

    public ListAdpater(ListContractor.Presenter actionPresenter, Context cnt) {
        this.presenter = actionPresenter;
        this.context = cnt;

    }

    @Override
    public ItemList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_layout, parent, false);
        return new ItemList(view, context);
    }

    @Override
    public void onBindViewHolder(ItemList holder, int position) {
        DataObject mData = dataList.get(holder.getAdapterPosition());
        holder.bind(mData);
    }

    @Override
    public int getItemCount() {
        if (dataList != null && !dataList.isEmpty()) {
            return dataList.size();
        } else {
            return 0;
        }
    }

    public void displayData(List<DataObject> mList) {
        this.dataList = mList;
    }
}
