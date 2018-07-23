package com.example.prashant.androidrestexercise.UserInterface.MVP;

import com.example.prashant.androidrestexercise.Logic.DataModel;

public interface ListContractor {
   interface View extends BaseView<Presenter> {

        void loadDataList(DataModel dataList);

        void displayErrorResponse(String errorMessage);

        void showLoading();

        void hideLoading();

    }

    interface Presenter{
        void getDataList();
    }
}





