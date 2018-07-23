package com.example.prashant.androidrestexercise.Logic;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class BaseUseCase {
    private static final String TAG = BaseUseCase.class.getSimpleName();

    private BaseProcess base;

    public BaseUseCase(BaseProcess baseProcess) {
        if (baseProcess != null) {
            base = baseProcess;
        }

    }

    public Observable<DataModel> getContent() {
        return Observable.defer(() -> {
            return base.getJsonContent();
        })
                .subscribeOn(Schedulers.io())
                .map(Response::body)
                .doOnError(throwable ->{ throwable.getMessage().toString();});


    }


}