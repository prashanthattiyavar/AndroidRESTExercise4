package com.example.prashant.androidrestexercise.UserInterface.MVP;

import com.example.prashant.androidrestexercise.Logic.BaseUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ListPresenter implements ListContractor.Presenter {


    private ListContractor.View view;
    private BaseUseCase baseUsecase;

    public ListPresenter(final BaseUseCase coreUseCase, final ListContractor.View conView) {
        baseUsecase = coreUseCase;
        view = conView;
    }

    @Override
    public void getDataList() {
        baseUsecase.getContent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.loadDataList(response),
                        error -> view.displayErrorResponse("Check your internet connection")
                );
    }

}
