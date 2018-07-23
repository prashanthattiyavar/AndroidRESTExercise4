package com.example.prashant.androidrestexercise.DepInjection;

import com.example.prashant.androidrestexercise.UserInterface.Fragment.ListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DIModule.class)
public interface DIComponent {
    void inject(ListFragment content);

}

