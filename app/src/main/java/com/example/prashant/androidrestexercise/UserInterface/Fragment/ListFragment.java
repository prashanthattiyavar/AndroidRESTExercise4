package com.example.prashant.androidrestexercise.UserInterface.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prashant.androidrestexercise.DepInjection.DIComponent;
import com.example.prashant.androidrestexercise.DepInjection.DIModule;
import com.example.prashant.androidrestexercise.DepInjection.DaggerDIComponent;
import com.example.prashant.androidrestexercise.Logic.BaseUseCase;
import com.example.prashant.androidrestexercise.Logic.DataModel;
import com.example.prashant.androidrestexercise.Logic.DataObject;
import com.example.prashant.androidrestexercise.R;
import com.example.prashant.androidrestexercise.UserInterface.MVP.ListAdpater;
import com.example.prashant.androidrestexercise.UserInterface.MVP.ListContractor;
import com.example.prashant.androidrestexercise.UserInterface.MVP.ListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ListContractor.View{

    private static final String TAG = ListFragment.class.getSimpleName();
    OnRowSelectedListener callback;

    @Inject
    BaseUseCase baseUsecase;

    @BindView(R.id.rootView)
    ViewGroup rootView;

    @BindView(R.id.recView)
    RecyclerView recView;

    private ListContractor.Presenter presenter;
    private ListAdpater adapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, root);

        DIComponent mActorComponent = DaggerDIComponent.builder().dIModule(new DIModule(getContext())).build();
        mActorComponent.inject(this);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new ListPresenter(baseUsecase, this);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdpater(presenter, getContext());
        recView.setAdapter(adapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnRowSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getDataList();
    }

    @Override
    public void loadDataList(DataModel dataList) {
        hideLoading();
        adapter.displayData(dataList.getmData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayErrorResponse(String errorMessage) {
        hideLoading();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        //progressBar.setVisibility(View.INVISIBLE);
    }

    public interface OnRowSelectedListener {
        public void onArticleSelected(DataObject mData);
    }

}
