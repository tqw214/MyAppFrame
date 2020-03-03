package com.viger.dagger;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.viger.dagger.bean.Student;
import com.viger.dagger.component.DaggerMainComponent;
import com.viger.dagger.module.MainModule;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    @Inject
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent.builder().mainModule(new MainModule()).build().inject(this);

        textView.setText(student.showMessage());
    }
}
