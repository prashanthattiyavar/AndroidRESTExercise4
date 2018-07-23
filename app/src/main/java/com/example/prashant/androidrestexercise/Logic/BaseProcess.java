package com.example.prashant.androidrestexercise.Logic;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface BaseProcess {
    @GET("facts")
    Observable<Response<DataModel>> getJsonContent();
}

