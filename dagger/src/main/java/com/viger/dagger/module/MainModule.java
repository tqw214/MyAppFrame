package com.viger.dagger.module;

import com.viger.dagger.bean.Student;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    public MainModule() {

    }

    @Singleton
    @Provides
    public Student provideStudent() {
        return new Student();
    }

}
