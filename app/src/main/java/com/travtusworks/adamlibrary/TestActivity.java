package com.travtusworks.adamlibrary;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.travtusworks.adam.AdamButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by teodora on 08.06.2018.
 */

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.adam_button)
    AdamButton adamButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activty_test);

        ButterKnife.bind(this);

        adamButton.setCompanyID(4);
        adamButton.setUserID(17);
        adamButton.setUserName("Teo");

    }
}
