package com.viger.dagger.component;

import com.viger.dagger.MainActivity;
import com.viger.dagger.module.MainModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
